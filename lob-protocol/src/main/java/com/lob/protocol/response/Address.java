package com.lob.protocol.response;

import com.lob.id.AddressId;
import com.neovisionaries.i18n.CountryCode;
import org.joda.time.DateTime;

import java.util.Date;

public class Address {
    private final AddressId id;
    private final String name;
    private final String email;
    private final String phone;
    private final String line1;
    private final String line2;
    private final String city;
    private final String state;
    private final String zip;
    private final CountryCode country;
    private final String object;
    private final DateTime dateCreated;
    private final DateTime dateModified;
    private final int deleted;

    public Address(
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
            final int deleted) {
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
}
