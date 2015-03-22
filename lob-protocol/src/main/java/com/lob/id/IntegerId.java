package com.lob.id;

public abstract class IntegerId {
    final int id;

    public IntegerId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }
}
