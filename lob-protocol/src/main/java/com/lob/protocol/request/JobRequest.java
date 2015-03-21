package com.lob.protocol.request;

import com.lob.ParamMapBuilder;
import com.lob.Or;
import com.lob.OrCollection;
import com.lob.id.AddressId;
import com.lob.id.LobObjectId;
import com.lob.id.ServiceId;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class JobRequest implements ParamMappable {
    final String name;
    final Or<AddressId, AddressRequest> to;
    final Or<AddressId, AddressRequest> from;
    final OrCollection<LobObjectId, LobObjectRequest> objects;
    final ServiceId service;

    public JobRequest(
            final String name,
            final Or<AddressId, AddressRequest> to,
            final Or<AddressId, AddressRequest> from,
            final OrCollection<LobObjectId, LobObjectRequest> objects,
            final ServiceId service) {
        this.name = name;
        this.to = checkNotNull(to, "to is required");
        this.from = checkNotNull(from, "from is required");
        this.objects = checkPresent(objects, "at least one object is required");
        this.service = service;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Map<String, List<String>> toParamMap() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("to", to)
            .put("from", from)
            .put("object", objects)
            .put("service", service)
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

    public OrCollection<LobObjectId, LobObjectRequest> getObjects() {
        return objects;
    }

    public ServiceId getService() {
        return service;
    }

    public static class Builder {
        private String name;
        private Or<AddressId, AddressRequest> to;
        private Or<AddressId, AddressRequest> from;
        private OrCollection<LobObjectId, LobObjectRequest> objects;
        private ServiceId service;

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

        public Builder from(final AddressId from) {
            this.from = Or.typeA(from);
            return this;
        }

        public Builder from(final AddressRequest from) {
            this.from = Or.typeB(from);
            return this;
        }

        public Builder objectIds(final Collection<LobObjectId> objects) {
            this.objects = OrCollection.typeA(objects);
            return this;
        }

        public Builder objects(final Collection<LobObjectRequest> objects) {
            this.objects = OrCollection.typeB(objects);
            return this;
        }

        public Builder service(final ServiceId service) {
            this.service = service;
            return this;
        }

        public JobRequest build() {
            return new JobRequest(name, to, from, objects, service);
        }
    }
}
