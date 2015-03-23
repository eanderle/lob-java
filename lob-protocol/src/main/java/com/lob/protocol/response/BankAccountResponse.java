package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.BankId;
import org.joda.time.DateTime;

public class BankAccountResponse {
    @JsonProperty private final BankId id;
    @JsonProperty private final AddressResponse bankAddress;
    @JsonProperty private final AddressResponse accountAddress;
    @JsonProperty private final String signatory;
    @JsonProperty private final DateTime dateCreated;
    @JsonProperty private final DateTime dateModified;
    @JsonProperty private final String object;

    @JsonCreator
    public BankAccountResponse(
            @JsonProperty("id") final BankId id,
            @JsonProperty("bank_address") final AddressResponse bankAddress,
            @JsonProperty("account_address") final AddressResponse accountAddress,
            @JsonProperty("signatory") final String signatory,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("object") final String object) {
        this.id = id;
        this.bankAddress = bankAddress;
        this.accountAddress = accountAddress;
        this.signatory = signatory;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.object = object;
    }

    public BankId getId() {
        return id;
    }

    public AddressResponse getBankAddress() {
        return bankAddress;
    }

    public AddressResponse getAccountAddress() {
        return accountAddress;
    }

    public String getSignatory() {
        return signatory;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "BankAccountResponse{" +
            "id=" + id +
            ", bankAddress=" + bankAddress +
            ", accountAddress=" + accountAddress +
            ", signatory='" + signatory + '\'' +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", object='" + object + '\'' +
            '}';
    }
}
