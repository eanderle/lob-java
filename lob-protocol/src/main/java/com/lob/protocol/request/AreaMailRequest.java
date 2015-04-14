package com.lob.protocol.request;

import com.lob.OrCollection;
import com.lob.ParamMapBuilder;
import com.lob.Util;
import com.lob.id.ZipCode;
import com.lob.id.ZipCodeRouteId;

import java.io.File;
import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class AreaMailRequest implements HasFileParams {
    public static final String FRONT = "front";
    public static final String BACK = "back";
    private final String name;
    private final FileParam front;
    private final FileParam back;
    private final OrCollection<ZipCode, ZipCodeRouteId> routes;
    private final TargetType targetType;
    private final Boolean fullBleed;

    public AreaMailRequest(
            final String name,
            final FileParam front,
            final FileParam back,
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

    private ParamMapBuilder toParamMapWithoutFiles() {
        return ParamMapBuilder.create()
            .put("name", name)
            .putAllStringValued("routes", routes)
            .put("target_type", targetType)
            .put("full_bleed", fullBleed);
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

    public FileParam getFront() {
        return front;
    }

    public FileParam getBack() {
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
        private FileParam front;
        private FileParam back;
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
