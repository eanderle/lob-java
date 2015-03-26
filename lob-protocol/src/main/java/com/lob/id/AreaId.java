package com.lob.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class AreaId extends LobId {
    private AreaId(final Prefix prefix, final String id) {
        super(prefix, id);
    }

    @JsonCreator
    public static AreaId parse(final String s) {
        return new AreaId(Prefix.AREA, s);
    }
}
