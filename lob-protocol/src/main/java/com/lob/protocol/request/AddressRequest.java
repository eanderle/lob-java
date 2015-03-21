package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.ParamMapBuilder;
import com.neovisionaries.i18n.CountryCode;

import java.util.List;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class AddressRequest implements ParamMappable {
    private final static int MAX_LENGTH = 50;

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

        if (checkPresent(name, "name is required").length() > MAX_LENGTH) {
            throw new IllegalArgumentException("name must not be longer than " + MAX_LENGTH + " characters");
        }
        this.name = name;

        this.email = email;
        this.phone = phone;
        this.line1 = checkPresent(line1, "address_line1 is required");
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = checkNotNull(country, "country is required");
    }

    @Override
    public Map<String, List<String>> toParamMap() {
        return ParamMapBuilder.create()
            .put("name", name)
            .put("email", email)
            .put("phone", phone)
            .put("address_line1", line1)
            .put("address_line2", line2)
            .put("address_city", city)
            .put("address_state", state)
            .put("address_zip", zip)
            .put("address_country", country)
            .build();
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
