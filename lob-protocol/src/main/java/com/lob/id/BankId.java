package com.lob.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class BankId extends LobId {
    private BankId(final Prefix prefix, final String id) {
        super(prefix, id);
    }

    @JsonCreator
    public static BankId parse(final String s) {
        return new BankId(Prefix.BANK, s);
    }
}
