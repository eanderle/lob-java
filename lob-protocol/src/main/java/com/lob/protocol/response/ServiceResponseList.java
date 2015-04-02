package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class ServiceResponseList {
    @JsonProperty private final Collection<ServiceResponse> data;
    @JsonProperty private final String object;

    @JsonCreator
    public ServiceResponseList(
            @JsonProperty("data") final Collection<ServiceResponse> data,
            @JsonProperty("object") final String object) {
        this.data = data;
        this.object = object;
    }

    public Collection<ServiceResponse> getData() {
        return data;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "ServiceResponseList{" +
            "data=" + data +
            ", object='" + object + '\'' +
            '}';
    }
}
