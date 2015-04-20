package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.Util;
import com.lob.protocol.request.BankAccountRequest;

import java.util.Collection;

public class BankAccountResponseList extends AbstractPagedResponseList<BankAccountResponse> {
    public BankAccountResponseList(
            @JsonProperty("data") final Collection<BankAccountResponse> data,
            @JsonProperty("object") final String object,
            @JsonProperty("next_url") final String nextUrl,
            @JsonProperty("previous_url") final String previousUrl,
            @JsonProperty("count") final int count) {
        super(data, object, nextUrl, previousUrl, count);
    }

    @Override
    public String toString() {
        return "BankAccountResponseList{" + super.toString() + "}";
    }
}
