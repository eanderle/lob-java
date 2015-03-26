package com.lob.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ZipCode extends StringId {
    private ZipCode(final String id) {
        super(id);
    }

    public ZipCodeRouteId toZipCodeRouteId(final RouteId routeId) {
        return ZipCodeRouteId.create(this, routeId);
    }

    @JsonCreator
    public static ZipCode parse(final String s) {
        return new ZipCode(s);
    }
}
