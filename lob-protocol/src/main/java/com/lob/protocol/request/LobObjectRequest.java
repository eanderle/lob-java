package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.ParamMapBuilder;
import com.lob.id.SettingId;

import java.util.Collection;
import java.util.Map;

public class LobObjectRequest implements ParamMappable {
    @JsonProperty("name") private final String name;
    @JsonProperty("file") private final String file;
    @JsonProperty("setting") private final SettingId setting;
    @JsonProperty("quantity") private final Integer quantity;
    @JsonProperty("double_sided") private final Boolean doubleSided;
    @JsonProperty("full_bleed") private final Boolean fullBleed;
    @JsonProperty("template") private final Boolean template;

    public LobObjectRequest(
            final String name,
            final String file,
            final SettingId setting,
            final Integer quantity,
            final Boolean doubleSided,
            final Boolean fullBleed,
            final Boolean template) {
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
    public Map<String, Collection<String>> toParamMap() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public Boolean isDoubleSided() {
        return doubleSided;
    }

    public Boolean isFullBleed() {
        return fullBleed;
    }

    public Boolean isTemplate() {
        return template;
    }

    @Override
    public String toString() {
        return "LobObjectRequest{" +
            "name='" + name + '\'' +
            ", file='" + file + '\'' +
            ", setting=" + setting +
            ", quantity=" + quantity +
            ", doubleSided=" + doubleSided +
            ", fullBleed=" + fullBleed +
            ", template=" + template +
            '}';
    }

    public static class Builder {
        private String name;
        private String file;
        private SettingId setting;
        private Integer quantity;
        private Boolean doubleSided;
        private Boolean fullBleed;
        private Boolean template;

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

        public Builder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder doubleSided(final Boolean doubleSided) {
            this.doubleSided = doubleSided;
            return this;
        }

        public Builder fullBleed(final Boolean fullBleed) {
            this.fullBleed = fullBleed;
            return this;
        }

        public Builder template(final Boolean template) {
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
