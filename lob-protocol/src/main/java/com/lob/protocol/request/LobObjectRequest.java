package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.ParamMapBuilder;
import com.lob.id.SettingId;

import java.util.List;
import java.util.Map;

public class LobObjectRequest implements ParamMappable {
    @JsonProperty("name") private final String name;
    @JsonProperty("file") private final String file;
    @JsonProperty("setting") private final SettingId setting;
    @JsonProperty("quantity") private final int quantity;
    @JsonProperty("double_sided") private final boolean doubleSided;
    @JsonProperty("full_bleed") private final boolean fullBleed;
    @JsonProperty("template") private final boolean template;

    public LobObjectRequest(
            final String name,
            final String file,
            final SettingId setting,
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

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Map<String, List<String>> toParamMap() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("file", file) // TODO
            .put("setting", setting)
            .put("quantity", quantity)
            .put("double_sided", doubleSided)
            .put("full_bleed", fullBleed)
            .put("template", template)
            .build();
    }

    public String getName() {
        return name;
    }

    public String getFile() {
        return file;
    }

    public SettingId getSetting() {
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

    public static class Builder {
        private String name;
        private String file;
        private SettingId setting;
        private int quantity;
        private boolean doubleSided;
        private boolean fullBleed;
        private boolean template;

        private Builder() {
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder file(final String file) {
            this.file = file;
            return this;
        }

        public Builder setting(final SettingId setting) {
            this.setting = setting;
            return this;
        }

        public Builder quantity(final int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder doubleSided(final boolean doubleSided) {
            this.doubleSided = doubleSided;
            return this;
        }

        public Builder fullBleed(final boolean fullBleed) {
            this.fullBleed = fullBleed;
            return this;
        }

        public Builder template(final boolean template) {
            this.template = template;
            return this;
        }

        public Builder butWith() {
            return new Builder()
                .name(name)
                .file(file)
                .setting(setting)
                .quantity(quantity)
                .doubleSided(doubleSided)
                .fullBleed(fullBleed)
                .template(template);
        }

        public LobObjectRequest build() {
            return new LobObjectRequest(name, file, setting, quantity, doubleSided, fullBleed, template);
        }
    }
}
