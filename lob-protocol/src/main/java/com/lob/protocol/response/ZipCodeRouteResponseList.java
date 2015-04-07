package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class ZipCodeRouteResponseList extends AbstractResponseList<ZipCodeRouteResponse> {
    public ZipCodeRouteResponseList(
        @JsonProperty("data") final Collection<ZipCodeRouteResponse> data,
        @JsonProperty("object") final String object) {
        super(data, object);
    }

    @Override
    public String toString() {
        return "ZipCodeRouteList" + super.toString();
    }
}
