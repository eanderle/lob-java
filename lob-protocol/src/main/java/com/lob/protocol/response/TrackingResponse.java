package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class TrackingResponse {
    @JsonProperty("id") private final String id;
    @JsonProperty("tracking_number") private final String trackingNumber;
	@JsonProperty("carrier") private final String carrier;
	@JsonProperty("link") private final String link;
	@JsonProperty("events") private final List events;
	@JsonProperty("object") private final String object;

    @JsonCreator
    public TrackingResponse(
        @JsonProperty("id") final String id,
        @JsonProperty("tracking_number") final String trackingNumber,
        @JsonProperty("carrier") final String carrier,
        @JsonProperty("link") final String link,
        @JsonProperty("events") final List events,
        @JsonProperty("object") final String object) {
            this.id = id;
            this.trackingNumber = trackingNumber;
            this.carrier = carrier;
            this.link = link;
            this.events = events;
            this.object = object;
    }

    public String getId() {
        return id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getLink() {
        return link;
    }

    public List getEvents() {
        return events;
    }

    public String getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "TrackingResponse{" +
            "id='" + id + '\'' +
            ", trackingNumber='" + trackingNumber + '\'' +
            ", carrier='" + carrier + '\'' +
            ", link='" + link + '\'' +
            ", events=" + events +
            ", object='" + object + '\'' +
            '}';
    }
}