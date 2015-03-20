package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.id.AddressId;
import com.neovisionaries.i18n.CountryCode;
import org.joda.time.DateTime;

public class AddressRequest {
    @JsonProperty("name") private final String name;
    @JsonProperty("email") private final String email;
    @JsonProperty("phone") private final String phone;
    @JsonProperty("address_line1") private final String line1;
    @JsonProperty("address_line2") private final String line2;
    @JsonProperty("address_city") private final String city;
    @JsonProperty("address_state") private final String state;
    @JsonProperty("address_zip") private final String zip;
    @JsonProperty("address_country") private final CountryCode country;

    @JsonCreator
    public AddressRequest(
            final String name,
            final String email,
            final String phone,
            final String line1,
            final String line2,
            final String city,
            final String state,
            final String zip,
            final CountryCode country) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
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
    }
}
