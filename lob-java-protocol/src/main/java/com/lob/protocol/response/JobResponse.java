package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.JobId;
import org.joda.time.DateTime;

import java.util.Collection;
import java.util.List;

import static com.lob.Util.defensiveCopy;

public class JobResponse extends AbstractLobResponse {
    @JsonProperty("id") private final JobId id;
    @JsonProperty("name") private final String name;
    @JsonProperty("price") private final String price;
    @JsonProperty("to") private final AddressResponse to;
    @JsonProperty("from") private final AddressResponse from;
    @JsonProperty("status") private final String status;
    @JsonProperty("tracking") private final TrackingResponse tracking;
    @JsonProperty("service") private final ServiceResponse service;
    @JsonProperty("objects") private final List<LobObjectResponse> objects;

    @JsonCreator
    public JobResponse(
            @JsonProperty("id") final JobId id,
            @JsonProperty("name") final String name,
            @JsonProperty("price") final String price,
            @JsonProperty("to") final AddressResponse to,
            @JsonProperty("from") final AddressResponse from,
            @JsonProperty("status") final String status,
            @JsonProperty("tracking") final TrackingResponse tracking,
            @JsonProperty("service") final ServiceResponse service,
            @JsonProperty("objects") final List<LobObjectResponse> objects,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("object") final String object) {
        super(dateCreated, dateModified, object);
        this.id = id;
        this.name = name;
        this.price = price;
        this.to = to;
        this.from = from;
        this.status = status;
        this.tracking = tracking;
        this.service = service;
        this.objects = objects;
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

    public TrackingResponse getTracking() {
        return tracking;
    }

    public ServiceResponse getService() {
        return service;
    }

    public List<LobObjectResponse> getObjects() {
        return defensiveCopy(objects);
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
            super.toString();
    }
}
