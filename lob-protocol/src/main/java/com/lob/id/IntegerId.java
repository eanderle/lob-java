package com.lob.id;

public abstract class IntegerId implements StringValued {
    final int id;

    protected IntegerId(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String value() {
        return Integer.toString(this.id);
    }

    @Override
    public String toString() {
        return value();
    }
}
