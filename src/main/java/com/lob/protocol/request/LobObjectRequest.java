package com.lob.protocol.request;

import com.lob.LobParamsBuilder;
import com.lob.id.SettingId;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;

public class LobObjectRequest extends AbstractDataFieldRequest implements HasLobParams {
    private final LobParam file;
    private final SettingId setting;
    private final Integer quantity;

    public final static String FILE_PARAM = "file";

    public LobObjectRequest(
            final String description,
            final LobParam file,
            final SettingId setting,
            final Integer quantity,
            final Map<String, String> metadata,
            final Map<String, String> data) {
        super(metadata, data, description);
        this.file = checkNotNull(file, "file is required");
        this.setting = checkNotNull(setting, "setting is required");
        this.quantity = quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Collection<LobParam> getLobParams() {
        return super.beginParams()
            .put("setting", setting)
            .put("quantity", quantity)
            .put(file)
            .build();
    }

    @Override
    public String toString() {
        return "LobObjectRequest{" +
            "file=" + file +
            ", setting=" + setting +
            ", quantity=" + quantity +
            super.toString();
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

    public static class Builder extends AbstractDataFieldRequest.Builder<Builder> {
        private LobParam file;
        private SettingId setting;
        private Integer quantity;

        private Builder() {
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

        public Builder setting(final int setting) {
            this.setting = SettingId.parse(setting);
            return this;
        }

        public Builder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder butWith() {
            return new Builder()
                .description(description)
                .file(file)
                .setting(setting)
                .quantity(quantity)
                .metadata(metadata)
                .data(data);
        }

        public LobObjectRequest build() {
            return new LobObjectRequest(description, file, setting, quantity, metadata, data);
        }
    }
}
