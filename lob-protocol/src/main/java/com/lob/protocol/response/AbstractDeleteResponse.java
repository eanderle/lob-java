package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.LobId;

public abstract class AbstractDeleteResponse<T extends LobId> {
    @JsonProperty private final boolean deleted;
    @JsonProperty private final T id;

    public AbstractDeleteResponse(
            @JsonProperty("deleted") final int deleted,
            @JsonProperty("id") final T id) {
        this.deleted = (deleted == 1);
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public T getId() {
        return id;
    }

    @Override
    public String toString() {
        return "{" +
            "deleted=" + deleted +
            ", id=" + id +
            '}';
    }
}
