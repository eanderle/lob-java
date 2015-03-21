package com.lob.protocol.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.AddressId;
import com.neovisionaries.i18n.CountryCode;
import org.joda.time.DateTime;

public class AddressResponse {
    @JsonProperty("id") private final AddressId id;
    @JsonProperty("name") private final String name;
    @JsonProperty("email") private final String email;
    @JsonProperty("phone") private final String phone;
    @JsonProperty("address_line1") private final String line1;
    @JsonProperty("address_line2") private final String line2;
    @JsonProperty("address_city") private final String city;
    @JsonProperty("address_state") private final String state;
    @JsonProperty("address_zip") private final String zip;
    @JsonProperty("address_country") private final CountryCode country;
    @JsonProperty("object") private final String object;
    @JsonProperty("date_created") private final DateTime dateCreated;
    @JsonProperty("date_modified") private final DateTime dateModified;
    @JsonProperty("deleted") private final boolean deleted;

    @JsonCreator
    public AddressResponse(
        final AddressId id,
        final String name,
        final String email,
        final String phone,
        final String line1,
        final String line2,
        final String city,
        final String state,
        final String zip,
        final CountryCode country,
        final String object,
        final DateTime dateCreated,
        final DateTime dateModified,
        final boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.object = object;
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.deleted = deleted;
    }

    public AddressId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public CountryCode getCountry() {
        return country;
    }

    public String getObject() {
        return object;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public DateTime getDateModified() {
        return dateModified;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
