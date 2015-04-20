package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractPagedResponseList<T> implements Iterable<T> {
    @JsonProperty private final Collection<T> data;
    @JsonProperty private final String object;
    @JsonProperty private final String nextUrl;
    @JsonProperty private final String previousUrl;
    @JsonProperty private final int count;

    @JsonCreator
    public AbstractPagedResponseList(
            @JsonProperty("data") final Collection<T> data,
            @JsonProperty("object") final String object,
            @JsonProperty("next_url") final String nextUrl,
            @JsonProperty("previous_url") final String previousUrl,
            @JsonProperty("count") final int count) {
        this.data = data;
        this.object = object;
        this.nextUrl = nextUrl;
        this.previousUrl = previousUrl;
        this.count = count;
    }

    @Override
    public Iterator<T> iterator() {
        return data.iterator();
    }

    public Collection<T> getData() {
        return data;
    }

    public String getObject() {
        return object;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "data=" + data +
            ", object='" + object + '\'' +
            ", nextUrl='" + nextUrl + '\'' +
            ", previousUrl='" + previousUrl + '\'' +
            ", count=" + count;
    }
}
