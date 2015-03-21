package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataItem {
    @JsonProperty("id") private final int id;
	@JsonProperty("name") private final String name;
	@JsonProperty("description") private final String description;
	@JsonProperty("object") private final String object;

    @JsonCreator
    public DataItem(
            final int id,
            final String name,
            final String description,
            final String object) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.object = object;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getObject() {
        return object;
    }
}