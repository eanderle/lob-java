package com.lob.protocol.request;

import com.lob.OrCollection;
import com.lob.ParamMapBuilder;
import com.lob.id.ZipCode;
import com.lob.id.ZipCodeRouteId;

import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class AreaMailRequest implements ParamMappable {
    private final String name;
    private final String front;
    private final String back;
    private final OrCollection<ZipCode, ZipCodeRouteId> routes;
    private final TargetType targetType;
    private final Boolean fullBleed;

    public AreaMailRequest(
            final String name,
            final String front,
            final String back,
            final OrCollection<ZipCode, ZipCodeRouteId> routes,
            final TargetType targetType,
            final Boolean fullBleed) {
        this.name = name;
        this.front = checkNotNull(front, "front is required");
        this.back = checkNotNull(back, "back is required");
        this.routes = checkPresent(routes, "routes is required");
        this.targetType = targetType;
        this.fullBleed = fullBleed;
    }

    @Override
    public Map<String, Collection<String>> toParamMap() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("front", front)
            .put("back", back)
            .putAllStringValued("routes", routes)
            .put("target_type", targetType)
            .put("full_bleed", fullBleed)
            .build();
    }

    public String getName() {
        return name;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public OrCollection<ZipCode, ZipCodeRouteId> getRoutes() {
        return routes;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public Boolean getFullBleed() {
        return fullBleed;
    }

    @Override
    public String toString() {
        return "AreaMailRequest{" +
            "name='" + name + '\'' +
            ", front='" + front + '\'' +
            ", back='" + back + '\'' +
            ", routes=" + routes +
            ", targetType=" + targetType +
            ", fullBleed=" + fullBleed +
            '}';
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String name;
        private String front;
        private String back;
        private OrCollection<ZipCode, ZipCodeRouteId> routes;
        private TargetType targetType;
        private Boolean fullBleed;

        private Builder() {
        }

        public Builder name(final String name) {
            this.name = name;
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

        public Builder routesForZips(final Collection<ZipCode> routes) {
            this.routes = OrCollection.typeA(routes);
            return this;
        }

        public Builder routesForIds(final Collection<ZipCodeRouteId> routes) {
            this.routes = OrCollection.typeB(routes);
            return this;
        }

        public Builder routes(final OrCollection<ZipCode, ZipCodeRouteId> routes) {
            this.routes = routes;
            return this;
        }

        public Builder targetType(final TargetType targetType) {
            this.targetType = targetType;
            return this;
        }

        public Builder fullBleed(final Boolean fullBleed) {
            this.fullBleed = fullBleed;
            return this;
        }

        public Builder butWith() {
            return new Builder().name(name).front(front).back(back).routes(routes).targetType(targetType).fullBleed(fullBleed);
        }

        public AreaMailRequest build() {
            return new AreaMailRequest(name, front, back, routes, targetType, fullBleed);
        }
    }
}
