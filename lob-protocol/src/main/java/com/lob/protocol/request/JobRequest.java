package com.lob.protocol.request;

import com.lob.LobParamsBuilder;
import com.lob.Or;
import com.lob.OrCollection;
import com.lob.Util;
import com.lob.id.AddressId;
import com.lob.id.CountryCode;
import com.lob.id.LobObjectId;
import com.lob.id.ServiceId;
import com.lob.protocol.response.AddressResponse;
import com.lob.protocol.response.LobObjectResponse;
import com.lob.protocol.response.LobObjectResponseList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class JobRequest implements HasLobParams {
    public static final String OBJECT = "object";

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
    public Collection<LobParam> getLobParams() {
        final LobParamsBuilder builder = LobParamsBuilder.create()
            .put("name", name)
            .put("to", to)
            .put("from", from)
            .put("service", service);

        int i = 1;
        if (this.objects.isTypeA()) {
            for (final LobObjectId id : this.objects.getTypeA()) {
                builder.put(OBJECT + i++, id.value());
            }
        }
        else {
            for (final LobObjectRequest request : this.objects.getTypeB()) {
                builder.putLobParams(OBJECT + i++, request);
            }
        }
        return builder.build();
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

    @Override
    public String toString() {
        return "JobRequest{" +
            "name='" + name + '\'' +
            ", to=" + to +
            ", from=" + from +
            ", objects=" + objects +
            ", service=" + service +
            '}';
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

        public Builder objectIds(final LobObjectId... objectIds) {
            return objectIds(Arrays.asList(objectIds));
        }

        public Builder objectIds(final Collection<LobObjectId> objectIds) {
            this.objects = OrCollection.typeA(objectIds);
            return this;
        }

        public Builder objectIds(final LobObjectResponseList objects) {
            final List<LobObjectId> idList = new ArrayList<LobObjectId>(objects.getCount());
            for (final LobObjectResponse response : objects) {
                idList.add(response.getId());
            }
            return objectIds(idList);
        }

        public Builder objects(final LobObjectRequest... objects) {
            return objects(Arrays.asList(objects));
        }

        public Builder objects(final Collection<LobObjectRequest> objects) {
            this.objects = OrCollection.typeB(objects);
            return this;
        }

        public Builder objects(final OrCollection<LobObjectId, LobObjectRequest> objects) {
            this.objects = objects;
            return this;
        }

        public Builder service(final ServiceId service) {
            this.service = service;
            return this;
        }

        public Builder butWith() {
            return new Builder().name(name).to(to).from(from).objects(objects).service(service);
        }
        public JobRequest build() {
            return new JobRequest(name, to, from, objects, service);
        }
    }
}
