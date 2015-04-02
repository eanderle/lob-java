package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class AreaMailResponseList extends ListResponse<AreaMailResponse> {
    public AreaMailResponseList(
            @JsonProperty("data") final Collection<AreaMailResponse> data,
            @JsonProperty("object") final String object,
            @JsonProperty("next_url") final String nextUrl,
            @JsonProperty("previous_url") final String previousUrl,
            @JsonProperty("count") final int count) {
        super(data, object, nextUrl, previousUrl, count);
    }

    @Override
    public String toString() {
        return "AreaMailResponseList{" + super.toString() + "}";
    }
}
