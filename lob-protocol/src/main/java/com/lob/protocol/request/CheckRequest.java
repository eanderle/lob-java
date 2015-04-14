package com.lob.protocol.request;

import com.lob.Or;
import com.lob.ParamMapBuilder;
import com.lob.Util;
import com.lob.id.AddressId;
import com.lob.id.BankAccountId;
import org.joda.money.Money;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;

public class CheckRequest implements HasFileParams {
    public static final String LOGO = "logo";

    private final String name;
    private final Integer checkNumber; // optional parameter, needs to be null if not set
    private final BankAccountId bankAccount;
    private final Or<AddressId, AddressRequest> to;
    private final Money amount;
    private final String message;
    private final String memo;
    private final FileParam logo;

    public CheckRequest(
            final String name,
            final Integer checkNumber,
            final BankAccountId bankAccount,
            final Or<AddressId, AddressRequest> to,
            final Money amount,
            final String message,
            final String memo,
            final FileParam logo) {
        this.name = name;
        this.checkNumber = checkNumber;
        this.bankAccount = checkNotNull(bankAccount, "bank account is required");
        this.to = checkNotNull(to, "to is required");
        this.amount = checkNotNull(amount, "amount is required");
        this.message = message;
        this.memo = memo;
        this.logo = logo;
    }

    private ParamMapBuilder toParamMapWithoutFiles() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("check_number", checkNumber)
            .put("bank_account", bankAccount)
            .put("to", to)
            .put("amount", amount)
            .put("message", message)
            .put("memo", memo);
    }

    @Override
    public Map<String, Collection<String>> toParamMap() {
        return toParamMapWithoutFiles().build();
    }

    @Override
    public Map<String, Collection<String>> toParamMapWithFiles() {
        return toParamMapWithoutFiles().put(LOGO, logo).build();
    }

    @Override
    public boolean isRequestWithFile() {
        return Util.isFileRequest(this.logo);
    }

    @Override
    public Collection<FileParam> getFileParams() {
        return Util.fileParamsAsList(this.logo);
    }

    public String getName() {
        return name;
    }

    public Integer getCheckNumber() {
        return checkNumber;
    }

    public BankAccountId getBankAccount() {
        return bankAccount;
    }

    public Or<AddressId, AddressRequest> getTo() {
        return to;
    }

    public Money getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getMemo() {
        return memo;
    }

    public FileParam getLogo() {
        return logo;
    }

    @Override
    public String toString() {
        return "CheckRequest{" +
            "name='" + name + '\'' +
            ", checkNumber=" + checkNumber +
            ", bankAccount=" + bankAccount +
            ", to=" + to +
            ", amount=" + amount +
            ", message='" + message + '\'' +
            ", memo='" + memo + '\'' +
            ", logo='" + logo + '\'' +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Integer checkNumber;
        private BankAccountId bankAccount;
        private Or<AddressId, AddressRequest> to;
        private Money amount;
        private String message;
        private String memo;
        private FileParam logo;

        private Builder() {}

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder checkNumber(final Integer checkNumber) {
            this.checkNumber = checkNumber;
            return this;
        }

        public Builder bankAccount(final BankAccountId bankAccount) {
            this.bankAccount = bankAccount;
            return this;
        }

        public Builder to(final String addressId) {
            this.to = Or.typeA(AddressId.parse(addressId));
            return this;
        }

        public Builder to(final AddressId to) {
            this.to = Or.typeA(to);
            return this;
        }

        public Builder to(final AddressRequest to) {
            this.to = Or.typeB(to);
            return this;
        }

        public Builder to(final Or<AddressId, AddressRequest> to) {
            this.to = to;
            return this;
        }

        public Builder amount(final Money amount) {
            this.amount = amount;
            return this;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder memo(final String memo) {
            this.memo = memo;
            return this;
        }

        public Builder logo(final String logo) {
            this.logo = FileParam.url(LOGO, logo);
            return this;
        }

        public Builder logo(final File logo) {
            this.logo = FileParam.file(LOGO, logo);
            return this;
        }

        public Builder logo(final FileParam logo) {
            this.logo = logo;
            return this;
        }

        public Builder butWith() {
            return new Builder().name(name).checkNumber(checkNumber).bankAccount(bankAccount).to(to).amount(amount).message(message).memo(memo).logo(logo);
        }

        public CheckRequest build() {
            return new CheckRequest(name, checkNumber, bankAccount, to, amount, message, memo, logo);
        }
    }
}
