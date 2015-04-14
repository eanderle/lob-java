package com.lob.protocol.request;

import com.lob.Or;
import com.lob.ParamMapBuilder;
import com.lob.Util;
import com.lob.id.AddressId;
import com.lob.id.SettingId;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;

public class PostcardRequest implements HasFileParams {
    private final static String FRONT = "front";
    private final static String BACK = "back";

    private final String name;
    private final Or<AddressId, AddressRequest> to;
    private final Or<AddressId, AddressRequest> from;
    private final String message;
    private final FileParam front;
    private final FileParam back;
    private final Boolean template;
    private final Boolean fullBleed;
    private final SettingId setting;

    public PostcardRequest(
            final String name,
            final Or<AddressId, AddressRequest> to,
            final Or<AddressId, AddressRequest> from,
            final String message,
            final FileParam front,
            final FileParam back,
            final Boolean template,
            final Boolean fullBleed,
            final SettingId setting) {

        this.name = name;
        this.to = checkNotNull(to, "to is required");
        this.from = checkNotNull(from, "from is required");
        this.message = message;
        this.front = checkNotNull(front, "front is required");
        this.back = back;
        this.template = template;
        this.fullBleed = fullBleed;
        this.setting = setting;
    }

    private ParamMapBuilder toParamMapWithoutFiles() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("to", to)
            .put("from", from)
            .put("message", message)
            .put("template", template)
            .put("full_bleed", fullBleed)
            .put("setting", setting);
    }

    @Override
    public Map<String, Collection<String>> toParamMap() {
        return toParamMapWithoutFiles().build();
    }

    @Override
    public Map<String, Collection<String>> toParamMapWithFiles() {
        return toParamMapWithoutFiles()
            .put(FRONT, front)
            .put(BACK, back)
            .build();
    }

    @Override
    public boolean isRequestWithFile() {
        return Util.isFileRequest(this.front, this.back);
    }

    @Override
    public Collection<FileParam> getFileParams() {
        return Util.fileParamsAsList(this.front, this.back);
    }

    public String getName() {
        return name;
    }

    public Or<AddressId, AddressRequest> getTo() {
        return to;
    }

    public Or<AddressId, AddressRequest> getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public FileParam getFront() {
        return front;
    }

    public FileParam getBack() {
        return back;
    }

    public Boolean isTemplate() {
        return template;
    }

    public Boolean isFullBleed() {
        return fullBleed;
    }

    public SettingId getSetting() {
        return setting;
    }

    @Override
    public String toString() {
        return "PostcardRequest{" +
            "name='" + name + '\'' +
            ", to=" + to +
            ", from=" + from +
            ", message='" + message + '\'' +
            ", front='" + front + '\'' +
            ", back='" + back + '\'' +
            ", template=" + template +
            ", fullBleed=" + fullBleed +
            ", setting=" + setting +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String name;
        private Or<AddressId, AddressRequest> to;
        private Or<AddressId, AddressRequest> from;
        private String message;
        private FileParam front;
        private FileParam back;
        private Boolean template;
        private Boolean fullBleed;
        private SettingId setting;

        private Builder() {}

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder to(final AddressId to) {
            this.to = Or.typeA(to);
            return this;
        }

        public Builder to(final AddressRequest to) {
            this.to = Or.typeB(to);
            return this;
        }

        public Builder to(final Or<AddressId, AddressRequest> to) {
            this.to = to;
            return this;
        }

        public Builder from(final AddressId from) {
            this.from = Or.typeA(from);
            return this;
        }

        public Builder from(final AddressRequest from) {
            this.from = Or.typeB(from);
            return this;
        }

        public Builder from(final Or<AddressId, AddressRequest> from) {
            this.from = from;
            return this;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder front(final String front) {
            this.front = FileParam.url(FRONT, front);
            return this;
        }

        public Builder front(final File front) {
            this.front = FileParam.file(FRONT, front);
            return this;
        }

        public Builder front(final FileParam front) {
            this.front = front;
            return this;
        }

        public Builder back(final String back) {
            this.back = FileParam.url(BACK, back);
            return this;
        }

        public Builder back(final File back) {
            this.back = FileParam.file(BACK, back);
            return this;
        }

        public Builder back(final FileParam back) {
            this.back = back;
            return this;
        }

        public Builder template(final Boolean template) {
            this.template = template;
            return this;
        }

        public Builder fullBleed(final Boolean fullBleed) {
            this.fullBleed = fullBleed;
            return this;
        }

        public Builder setting(final SettingId setting) {
            this.setting = setting;
            return this;
        }

        public Builder butWith() {
            return new Builder().name(name).to(to).from(from).message(message).front(front).back(back).template(template).fullBleed(fullBleed).setting(setting);
        }

        public PostcardRequest build() {
            return new PostcardRequest(name, to, from, message, front, back, template, fullBleed, setting);
        }
    }
}
