package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class SettingResponseList {
    @JsonProperty private final Collection<SettingResponse> data;
    @JsonProperty private final String object;

    @JsonCreator
    public SettingResponseList(
            @JsonProperty("data") final Collection<SettingResponse> data,
            @JsonProperty("object") final String object) {
        this.data = data;
        this.object = object;
    }

    public Collection<SettingResponse> getData() {
        return data;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "SettingResponseList{" +
            "data=" + data +
            ", object='" + object + '\'' +
            '}';
    }
}
