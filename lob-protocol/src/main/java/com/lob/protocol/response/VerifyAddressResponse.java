package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.AddressId;
import org.joda.time.DateTime;

public class VerifyAddressResponse extends AbstractAddressResponse {
    @JsonCreator
    public VerifyAddressResponse(
            @JsonProperty("address_line1") final String line1,
            @JsonProperty("address_line2") final String line2,
            @JsonProperty("address_city") final String city,
            @JsonProperty("address_state") final String state,
            @JsonProperty("address_zip") final String zip,
            @JsonProperty("address_country") final String country,
            @JsonProperty("object") final String object) {

        super(line1, line2, city, state, zip, country, object);
    }

    @Override
    public String toString() {
        return "VerifyAddressResponse{" + super.toString();
    }
}
