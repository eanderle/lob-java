package com.lob.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RouteId extends StringId {
    private RouteId(final String id) {
        super(id);
    }

    public ZipCodeRouteId toZipCodeRouteId(final ZipCode zipCode) {
        return ZipCodeRouteId.create(zipCode, this);
    }

    @JsonCreator
    public static RouteId parse(final String s) {
        return new RouteId(s);
    }
}
