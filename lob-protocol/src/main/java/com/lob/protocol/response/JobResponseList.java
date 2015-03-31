package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class JobResponseList extends ListResponse {
    @JsonProperty private final Collection<JobResponse> data;

    public JobResponseList(
            @JsonProperty("data") final Collection<JobResponse> data,
            @JsonProperty("object") final String object,
            @JsonProperty("next_url") final String nextUrl,
            @JsonProperty("previous_url") final String previousUrl,
            @JsonProperty("count") final int count) {
        super(object, nextUrl, previousUrl, count);
        this.data = data;
    }

    public Collection<JobResponse> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "JobResponseList{" +
            "data=" + data + super.toString();
    }
}
