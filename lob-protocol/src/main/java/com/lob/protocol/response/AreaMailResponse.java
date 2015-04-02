package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.AreaMailId;
import com.lob.protocol.request.TargetType;
import org.joda.money.Money;
import org.joda.time.DateTime;

import java.util.Collection;

public class AreaMailResponse {
    @JsonProperty private final AreaMailId id;
    @JsonProperty private final String name;
    @JsonProperty private final String status;
    @JsonProperty private final Money price;
    @JsonProperty private final String url;
    @JsonProperty private final TargetType targetType;
    @JsonProperty private final int numAddresses;
    @JsonProperty private final Collection<ZipCodeRouteCollection> zipCodeRouteCollections;
    @JsonProperty private final Collection<ThumbnailResponse> thumbnails;
    @JsonProperty private final DateTime expectedDeliveryDate;
    @JsonProperty private final DateTime dateCreated;
    @JsonProperty private final DateTime dateModified;
    @JsonProperty private final String object;

    @JsonCreator
    public AreaMailResponse(
            @JsonProperty("id") final AreaMailId id,
            @JsonProperty("name") final String name,
            @JsonProperty("status") final String status,
            @JsonProperty("price") final Money price,
            @JsonProperty("url") final String url,
            @JsonProperty("target_type") final TargetType targetType,
            @JsonProperty("addresses") final int numAddresses,
            @JsonProperty("zip_codes") final Collection<ZipCodeRouteCollection> zipCodeRouteCollections,
            @JsonProperty("thumbnails") final Collection<ThumbnailResponse> thumbnails,
            @JsonProperty("expected_delivery_date") final DateTime expectedDeliveryDate,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("object") final String object) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.price = price;
        this.url = url;
        this.targetType = targetType;
        this.numAddresses = numAddresses;
        this.zipCodeRouteCollections = zipCodeRouteCollections;
        this.thumbnails = thumbnails;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.object = object;
    }

    public AreaMailId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public Money getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public int getNumAddresses() {
        return numAddresses;
    }

    public Collection<ZipCodeRouteCollection> getZipCodeRouteCollections() {
        return zipCodeRouteCollections;
    }

    public Collection<ThumbnailResponse> getThumbnails() {
        return thumbnails;
    }

    public DateTime getExpectedDeliveryDate() {
        return expectedDeliveryDate;
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
        return "AreaMailResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", status='" + status + '\'' +
            ", price=" + price +
            ", url='" + url + '\'' +
            ", targetType=" + targetType +
            ", numAddresses=" + numAddresses +
            ", zipCodeRouteCollections=" + zipCodeRouteCollections +
            ", thumbnails=" + thumbnails +
            ", expectedDeliveryDate=" + expectedDeliveryDate +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", object='" + object + '\'' +
            '}';
    }
}
