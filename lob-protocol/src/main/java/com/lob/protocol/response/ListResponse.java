package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ListResponse {
    @JsonProperty private final String object;
    @JsonProperty private final String nextUrl;
    @JsonProperty private final String previousUrl;
    @JsonProperty private final int count;

    @JsonCreator
    public ListResponse(
            @JsonProperty("object") final String object,
            @JsonProperty("next_url") final String nextUrl,
            @JsonProperty("previous_url") final String previousUrl,
            @JsonProperty("count") final int count) {
        this.object = object;
        this.nextUrl = nextUrl;
        this.previousUrl = previousUrl;
        this.count = count;
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
        return ", object='" + object + '\'' +
            ", nextUrl='" + nextUrl + '\'' +
            ", previousUrl='" + previousUrl + '\'' +
            ", count=" + count +
            '}';
    }
}
