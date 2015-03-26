package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.ZipCode;

import java.util.Collection;

public class ZipCodeRouteCollection {
    @JsonProperty private final ZipCode zipCode;
    @JsonProperty private final Collection<RouteResponse> routes;

    @JsonCreator
    public ZipCodeRouteCollection(
            @JsonProperty("zip_code") final ZipCode zipCode,
            @JsonProperty("routes") final Collection<RouteResponse> routes) {
        this.zipCode = zipCode;
        this.routes = routes;
    }

    public ZipCode getZipCode() {
        return zipCode;
    }

    public Collection<RouteResponse> getRoutes() {
        return routes;
    }

    @Override
    public String toString() {
        return "ZipCodeRouteCollection{" +
            "zipCode=" + zipCode +
            ", routes=" + routes +
            '}';
    }
}
