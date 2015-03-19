package com.lob.id;

public class AddressId extends LobId {
    private AddressId(final Prefix prefix, final String identifier) {
        super(prefix, identifier);
    }

    public static AddressId parse(final String s) {
        return new AddressId(Prefix.ADDRESS, s);
    }
}
