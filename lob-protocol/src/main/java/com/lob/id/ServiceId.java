package com.lob.id;

public class ServiceId implements IntegerId {
    final int id;

    public ServiceId(final int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
