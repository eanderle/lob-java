package com.lob.id;

public class LobObjectId extends LobId {
    private LobObjectId(final Prefix prefix, final String identifier) {
        super(prefix, identifier);
    }

    public static LobObjectId parse(final String s) {
        return new LobObjectId(Prefix.OBJECT, s);
    }
}
