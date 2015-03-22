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
    @JsonProperty("address_country") private final String country;
    @JsonProperty("object") private final String object;
    @JsonProperty("date_created") private final DateTime dateCreated;
    @JsonProperty("date_modified") private final DateTime dateModified;
    @JsonProperty("deleted") private final boolean deleted;

    @JsonCreator
    public AddressResponse(
            @JsonProperty("id") final AddressId id,
            @JsonProperty("name") final String name,
            @JsonProperty("email") final String email,
            @JsonProperty("phone") final String phone,
            @JsonProperty("address_line1") final String line1,
            @JsonProperty("address_line2") final String line2,
            @JsonProperty("address_city") final String city,
            @JsonProperty("address_state") final String state,
            @JsonProperty("address_zip") final String zip,
            @JsonProperty("address_country") final String country,
            @JsonProperty("object") final String object,
            @JsonProperty("date_created") final DateTime dateCreated,
            @JsonProperty("date_modified") final DateTime dateModified,
            @JsonProperty("deleted") final boolean deleted) {
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

    public String getCountry() {
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

    @Override
    public String toString() {
        return "AddressResponse{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", line1='" + line1 + '\'' +
            ", line2='" + line2 + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", country='" + country + '\'' +
            ", object='" + object + '\'' +
            ", dateCreated=" + dateCreated +
            ", dateModified=" + dateModified +
            ", deleted=" + deleted +
            '}';
    }
}
