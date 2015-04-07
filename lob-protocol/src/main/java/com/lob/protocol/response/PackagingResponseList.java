package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.ws.Response;
import java.util.Collection;

public class PackagingResponseList extends AbstractResponseList<PackagingResponse> {
    public PackagingResponseList(
            @JsonProperty("data") final Collection<PackagingResponse> data,
            @JsonProperty("object") final String object) {
        super(data, object);
    }
    @Override
    public String toString() {
        return "PackagingResponseList" + super.toString();
    }
}
