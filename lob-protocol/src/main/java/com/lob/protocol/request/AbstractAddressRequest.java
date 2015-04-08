package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lob.ParamMapBuilder;
import com.neovisionaries.i18n.CountryCode;

import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public abstract class AbstractAddressRequest {
    private final static int MAX_LENGTH = 50;

    private final String line1;
    private final String line2;
    private final String city;
    private final String state;
    private final String zip;
    private final CountryCode country;

    public AbstractAddressRequest(
        final String line1,
        final String line2,
        final String city,
        final String state,
        final String zip,
        final CountryCode country) {

        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public ParamMapBuilder beginParamMap() {
        return ParamMapBuilder.create()
            .put("address_line1", line1)
            .put("address_line2", line2)
            .put("address_city", city)
            .put("address_state", state)
            .put("address_zip", zip)
            .put("address_country", country);
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

    @Override
    public String toString() {
        return
            ", line1='" + line1 + '\'' +
            ", line2='" + line2 + '\'' +
            ", city='" + city + '\'' +
            ", state='" + state + '\'' +
            ", zip='" + zip + '\'' +
            ", country=" + country +
            '}';
    }
}
