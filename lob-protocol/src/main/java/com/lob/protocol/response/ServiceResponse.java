package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ServiceResponse {
    @JsonProperty("object") private final String object;
	@JsonProperty("data") private final List<DataItem> data;

    @JsonCreator
    public ServiceResponse(
            final String object,
            final List<DataItem> data) {
        this.object = object;
        this.data = data;
    }

    public String getObject() {
        return object;
    }

    public List<DataItem> getData() {
        return data;
    }
}