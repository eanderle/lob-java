package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;

import static com.lob.Util.defensiveCopy;

public class ServiceResponse {
    @JsonProperty("object") private final String object;
	@JsonProperty("data") private final Collection<DataItem> data;

    @JsonCreator
    public ServiceResponse(
            @JsonProperty("object") final String object,
            @JsonProperty("data") final Collection<DataItem> data) {
        this.object = object;
        this.data = data;
    }

    public String getObject() {
        return object;
    }

    public Collection<DataItem> getData() {
        return defensiveCopy(data);
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
            "object='" + object + '\'' +
            ", data=" + data +
            '}';
    }
}