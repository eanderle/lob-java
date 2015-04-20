package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.Or;
import com.lob.id.AddressId;
import com.lob.id.BankAccountId;
import com.lob.protocol.request.AddressRequest;
import com.lob.protocol.request.BankAccountRequest;
import org.joda.time.DateTime;

public class BankAccountResponse {
    @JsonProperty private final BankAccountId id;
    @JsonProperty private final String name;
    @JsonProperty private final String routingNumber;
    @JsonProperty private final String accountNumber;
    @JsonProperty private final AddressResponse bankAddress;
    @JsonProperty private final AddressResponse accountAddress;
    @JsonProperty private final String signatory;
    @JsonProperty private final DateTime dateCreated;
    @JsonProperty private final DateTime dateModified;
    @JsonProperty private final String object;

    @JsonCreator
    public BankAccountResponse(
            @JsonProperty("id") final BankAccountId id,
            @JsonProperty("name") final String name,
            @JsonProperty("routing_number") final String routingNumber,
            @JsonProperty("account_number") final String accountNumber,
            @JsonProperty("bank_address") final AddressResponse bankAddress,
            @JsonProperty("account_address") final AddressResponse accountAddress,
            @JsonProperty("signatory") final String signatory,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("object") final String object) {
        this.id = id;
        this.name = name;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.bankAddress = bankAddress;
        this.accountAddress = accountAddress;
        this.signatory = signatory;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.object = object;
    }

    public BankAccountId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
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
            ", name='" + name + '\'' +
            ", routingNumber='" + routingNumber + '\'' +
            ", accountNumber='" + accountNumber + '\'' +
            ", bankAddress=" + bankAddress +
            ", accountAddress=" + accountAddress +
            ", signatory='" + signatory + '\'' +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", object='" + object + '\'' +
            '}';
    }
}
