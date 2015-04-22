package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

import static com.lob.Util.defensiveCopy;

public class ErrorResponse {
    @JsonProperty private final Collection<ErrorResponseItem> errors;

    public ErrorResponse(@JsonProperty("errors") final Collection<ErrorResponseItem> errors) {
        this.errors = errors;
    }

    public Collection<ErrorResponseItem> getErrors() {
        return defensiveCopy(errors);
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
            "errors=" + errors +
            '}';
    }
}
