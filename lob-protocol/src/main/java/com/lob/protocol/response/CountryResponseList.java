package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class CountryResponseList extends AbstractResponseList<CountryResponse> {
    public CountryResponseList(
            @JsonProperty("data") final Collection<CountryResponse> data,
            @JsonProperty("object") final String object) {
        super(data, object);
    }
    @Override
    public String toString() {
        return "CountryResponseList" + super.toString();
    }
}
