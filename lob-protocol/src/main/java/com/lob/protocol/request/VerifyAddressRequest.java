package com.lob.protocol.request;

import com.neovisionaries.i18n.CountryCode;

import java.util.Collection;
import java.util.Map;

import static com.lob.Util.checkNotNull;
import static com.lob.Util.checkPresent;

public class VerifyAddressRequest extends AbstractAddressRequest implements ParamMappable {
    public VerifyAddressRequest(
        final String line1,
        final String line2,
        final String city,
        final String state,
        final String zip,
        final CountryCode country) {

        super(line1, line2, city, state, zip, country);
    }

    @Override
    public Map<String, Collection<String>> toParamMap() {
        return super.beginParamMap().build();
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return "VerifyAddressRequest{" + super.toString();
    }

    public static class Builder {
        private String line1;
        private String line2;
        private String city;
        private String state;
        private String zip;
        private CountryCode country;

        private Builder() {
        }

        public Builder line1(final String line1) {
            this.line1 = line1;
            return this;
        }

        public Builder line2(final String line2) {
            this.line2 = line2;
            return this;
        }

        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        public Builder state(final String state) {
            this.state = state;
            return this;
        }

        public Builder zip(final String zip) {
            this.zip = zip;
            return this;
        }

        public Builder country(final CountryCode country) {
            this.country = country;
            return this;
        }

        public Builder butWith() {
            return new Builder()
                .line1(line1)
                .line2(line2)
                .city(city)
                .state(state)
                .zip(zip)
                .country(country);
        }

        public VerifyAddressRequest build() {
            return new VerifyAddressRequest(line1, line2, city, state, zip, country);
        }
    }
}
