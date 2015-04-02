package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.LobObjectId;
import org.joda.time.DateTime;

public class LobObjectResponse {
    @JsonProperty private final LobObjectId id;
    @JsonProperty private final String name;
    @JsonProperty private final int quantity;
    @JsonProperty private final boolean fullBleed;
    @JsonProperty private final boolean doubleSided;
    @JsonProperty private final boolean template;
    @JsonProperty private final DateTime dateCreated;
    @JsonProperty private final DateTime dateModified;
    @JsonProperty private final SettingResponse setting;
    @JsonProperty private final String object;

    @JsonCreator
    public LobObjectResponse(
            @JsonProperty("id") final LobObjectId id,
            @JsonProperty("name") final String name,
            @JsonProperty("quantity") final int quantity,
            @JsonProperty("full_bleed") final boolean fullBleed,
            @JsonProperty("double_sided") final boolean doubleSided,
            @JsonProperty("template") final boolean template,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("setting") final SettingResponse setting,
            @JsonProperty("object") final String object) {
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

    @Override
    public String toString() {
        return "LobObjectResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", quantity=" + quantity +
            ", fullBleed=" + fullBleed +
            ", doubleSided=" + doubleSided +
            ", template=" + template +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", setting=" + setting +
            ", object='" + object + '\'' +
            '}';
    }
}
