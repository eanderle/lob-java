package com.lob.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class ServiceId extends IntegerId {
    public ServiceId(final int id) {
        super(id);
    }

    @JsonCreator
    public static ServiceId parse(final String s) {
        return new ServiceId(Integer.valueOf(s));
    }
}
