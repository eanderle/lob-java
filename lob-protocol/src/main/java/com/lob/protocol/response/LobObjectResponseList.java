package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.Util;
import com.lob.protocol.request.LobObjectRequest;

import java.util.Collection;

public class LobObjectResponseList extends AbstractPagedResponseList<LobObjectResponse> implements RequestListTransformer<LobObjectRequest> {
    public LobObjectResponseList(
            @JsonProperty("data") final Collection<LobObjectResponse> data,
            @JsonProperty("object") final String object,
            @JsonProperty("next_url") final String nextUrl,
            @JsonProperty("previous_url") final String previousUrl,
            @JsonProperty("count") final int count) {
        super(data, object, nextUrl, previousUrl, count);
    }

    @Override
    public Collection<LobObjectRequest> toRequest() {
        return Util.toRequestList(getData());
    }

    @Override
    public String toString() {
        return "LobObjectResponseList{" + super.toString() + "}";
    }
}
