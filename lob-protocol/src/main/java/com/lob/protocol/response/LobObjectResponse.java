package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.LobObjectId;
import org.joda.time.DateTime;

public class LobObjectResponse {
    private final LobObjectId id;
    @JsonProperty("name") private final String name;
    @JsonProperty("quantity") private final int quantity;
    @JsonProperty("full_bleed") private final boolean fullBleed;
    @JsonProperty("double_sided") private final boolean doubleSided;
    @JsonProperty("template") private final boolean template;
    private final DateTime dateCreated;
    private final DateTime dateModified;
    @JsonProperty("setting") private final SettingResponse setting;
    private final String object;

    public LobObjectResponse(
            final LobObjectId id,
            final String name,
            final int quantity,
            final boolean fullBleed,
            final boolean doubleSided,
            final boolean template,
            final DateTime dateCreated,
            final DateTime dateModified,
            final SettingResponse setting,
            final String object) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.fullBleed = fullBleed;
        this.doubleSided = doubleSided;
        this.template = template;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.setting = setting;
        this.object = object;
    }

    public LobObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isFullBleed() {
        return fullBleed;
    }

    public boolean isDoubleSided() {
        return doubleSided;
    }

    public boolean isTemplate() {
        return template;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    public SettingResponse getSetting() {
        return setting;
    }

    public String getObject() {
        return object;
    }
}
