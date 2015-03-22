package com.lob.protocol.response;

import com.lob.id.JobId;
import org.joda.time.DateTime;

import java.util.Collection;

public class JobResponse {
    final JobId id;
    final String name;
    final String price;
    final AddressResponse to;
    final AddressResponse from;
    final String status;
    final String tracking;
    final ServiceResponse service;
    final Collection<LobObjectResponse> objects;
    final DateTime dateCreated;
    final DateTime dateModified;
    final String object;

    public JobResponse(
            final JobId id,
            final String name,
            final String price,
            final AddressResponse to,
            final AddressResponse from,
            final String status,
            final String tracking,
            final ServiceResponse service,
            final Collection<LobObjectResponse> objects,
            final DateTime dateCreated,
            final DateTime dateModified,
            final String object) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.to = to;
        this.from = from;
        this.status = status;
        this.tracking = tracking;
        this.service = service;
        this.objects = objects;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.object = object;
    }

    public JobId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public AddressResponse getTo() {
        return to;
    }

    public AddressResponse getFrom() {
        return from;
    }

    public String getStatus() {
        return status;
    }

    public String getTracking() {
        return tracking;
    }

    public ServiceResponse getService() {
        return service;
    }

    public Collection<LobObjectResponse> getObjects() {
        return objects;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "JobResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price='" + price + '\'' +
            ", to=" + to +
            ", from=" + from +
            ", status='" + status + '\'' +
            ", tracking='" + tracking + '\'' +
            ", service=" + service +
            ", objects=" + objects +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", object='" + object + '\'' +
            '}';
    }
}
