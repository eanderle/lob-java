package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.AddressId;
import org.joda.time.DateTime;

public class VerifyAddressResponse {
    @JsonProperty private final AbstractAddressResponse address;

    @JsonCreator
    public VerifyAddressResponse(@JsonProperty("address") final AbstractAddressResponse address) {
        this.address = address;
    }

    public String getLine1() {
        return address.getLine1();
    }

    public String getCountry() {
        return address.getCountry();
    }

    public String getState() {
        return address.getState();
    }

    public String getZip() {
        return address.getZip();
    }

    public String getCity() {
        return address.getCity();
    }

    public String getLine2() {
        return address.getLine2();
    }

    public String getObject() {
        return address.getObject();
    }

    @Override
    public String toString() {
        return "VerifyAddressResponse{" +
            "address=" + address +
            '}';
    }
}
