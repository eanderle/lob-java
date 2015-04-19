package com.lob;

import com.lob.protocol.response.ErrorResponse;

public class LobApiException extends RuntimeException {
    private final ErrorResponse errorResponse;

    public LobApiException(final ErrorResponse errorResponse) {
        super(errorResponse.getErrors().toString());
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    @Override
    public String toString() {
        return "LobApiException: " + errorResponse.getErrors().toString();
    }
}
