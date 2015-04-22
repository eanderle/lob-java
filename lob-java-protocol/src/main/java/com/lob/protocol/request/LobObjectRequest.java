package com.lob.protocol.request;

import com.lob.LobParamsBuilder;
import com.lob.id.SettingId;

import java.io.File;
import java.util.Collection;

import static com.lob.Util.checkNotNull;

public class LobObjectRequest implements HasLobParams {
    private final String name;
    private final LobParam file;
    private final SettingId setting;
    private final Integer quantity;
    private final Boolean doubleSided;
    private final Boolean fullBleed;
    private final Boolean template;

    public final static String FILE_PARAM = "file";

    public LobObjectRequest(
            final String name,
            final LobParam file,
            final SettingId setting,
            final Integer quantity,
            final Boolean doubleSided,
            final Boolean fullBleed,
            final Boolean template) {
        this.name = name;
        this.file = checkNotNull(file, "file is required");
        this.setting = checkNotNull(setting, "setting is required");
        this.quantity = quantity;
        this.doubleSided = doubleSided;
        this.fullBleed = fullBleed;
        this.template = template;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Collection<LobParam> getLobParams() {
        return LobParamsBuilder.create()
            .put("name", name)
            .put("setting", setting)
            .put("quantity", quantity)
            .put("double_sided", doubleSided)
            .put("full_bleed", fullBleed)
            .put("template", template)
            .put(file)
            .build();
    }

    public String getName() {
        return name;
    }

    public LobParam getFile() {
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
        private LobParam file;
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

        public Builder file(final LobParam file) {
            this.file = file;
            return this;
        }

        public Builder file(final String file) {
            this.file = LobParam.strings(FILE_PARAM, file);
            return this;
        }

        public Builder file(final File file) {
            this.file = LobParam.file(FILE_PARAM, file);
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
