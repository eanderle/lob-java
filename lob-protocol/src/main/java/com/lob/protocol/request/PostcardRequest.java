package com.lob.protocol.request;

import com.lob.Or;
import com.lob.ParamMapBuilder;
import com.lob.id.AddressId;
import com.lob.id.SettingId;
import com.lob.protocol.response.AddressResponse;

import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class PostcardRequest implements ParamMappable {
    private final String name;
    private final Or<AddressId, AddressRequest> to;
    private final Or<AddressId, AddressRequest> from;
    private final String message;
    private final String front;
    private final String back;
    private final boolean template;
    private final boolean fullBleed;
    private final SettingId setting;

    public PostcardRequest(
            final String name,
            final Or<AddressId, AddressRequest> to,
            final Or<AddressId, AddressRequest> from,
            final String message,
            final String front,
            final String back,
            final boolean template,
            final boolean fullBleed,
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

    @Override
    public Map<String, Collection<String>> toParamMap() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("to", to)
            .put("from", from)
            .put("message", message)
            .put("front", front)
            .put("back", back)
            .put("template", template)
            .put("full_bleed", fullBleed)
            .put("setting", setting)
            .build();

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

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public boolean isTemplate() {
        return template;
    }

    public boolean isFullBleed() {
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
        private String front;
        private String back;
        private boolean template;
        private boolean fullBleed;
        private SettingId setting;

        private Builder() {
        }

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
            this.front = front;
            return this;
        }

        public Builder back(final String back) {
            this.back = back;
            return this;
        }

        public Builder template(final boolean template) {
            this.template = template;
            return this;
        }

        public Builder fullBleed(final boolean fullBleed) {
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
