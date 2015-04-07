package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class StateResponseList extends AbstractResponseList<StateResponse> {
    public StateResponseList(
            @JsonProperty("data") final Collection<StateResponse> data,
            @JsonProperty("object") final String object) {
        super(data, object);
    }
    @Override
    public String toString() {
        return "StateResponseList" + super.toString();
    }
}
