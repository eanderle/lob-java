package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public abstract class AbstractResponseList<T> {
    @JsonProperty private final Collection<T> data;
    @JsonProperty private final String object;

    @JsonCreator
    public AbstractResponseList(
        @JsonProperty("data") final Collection<T> data,
        @JsonProperty("object") final String object) {
        this.data = data;
        this.object = object;
    }

    public Collection<T> getData() {
        return data;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "{" +
            "data=" + data +
            ", object='" + object + '\'' +
            '}';
    }
}
