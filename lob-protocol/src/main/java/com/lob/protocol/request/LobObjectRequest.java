package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.protocol.Setting;

public class LobObjectRequest {
    @JsonProperty("name") private final String name;
    @JsonProperty("file") private final String file;
    @JsonProperty("setting") private final SettingRequest setting;
    @JsonProperty("quantity") private final int quantity;
    @JsonProperty("double_sided") private final boolean doubleSided;
    @JsonProperty("full_bleed") private final boolean fullBleed;
    @JsonProperty("template") private final boolean template;

    public LobObjectRequest(
            final String name,
            final String file,
            final SettingRequest setting,
            final int quantity,
            final boolean doubleSided,
            final boolean fullBleed,
            final boolean template) {
        this.name = name;
        this.file = file;
        this.setting = setting;
        this.quantity = quantity;
        this.doubleSided = doubleSided;
        this.fullBleed = fullBleed;
        this.template = template;
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }

    public SettingRequest getSetting() {
        return setting;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isDoubleSided() {
        return doubleSided;
    }

    public boolean isFullBleed() {
        return fullBleed;
    }

    public boolean isTemplate() {
        return template;
    }
}
