package com.lob.protocol.request;

import com.lob.LobParamsBuilder;
import com.lob.id.ZipCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.lob.Util.defensiveCopy;

public class ZipCodeRouteRequest implements HasLobParams {
    private final Collection<ZipCode> zipCodes;

    public ZipCodeRouteRequest(final Collection<ZipCode> zipCodes) {
        this.zipCodes = defensiveCopy(zipCodes);
    }

    @Override
    public Collection<LobParam> getLobParams() {
        return LobParamsBuilder.create()
            .putAll("zip_codes", this.zipCodes)
            .build();
    }

    public Collection<ZipCode> getZipCodes() {
        return defensiveCopy(zipCodes);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Collection<ZipCode> zipCodes = new ArrayList<ZipCode>();

        private Builder() {
        }

        public Builder addStringZip(final String zipCode) {
            this.zipCodes.add(ZipCode.parse(zipCode));
            return this;
        }

        public Builder addZip(final ZipCode zipCode) {
            this.zipCodes.add(zipCode);
            return this;
        }

        public Builder addStringZips(final String... zipCodes) {
            return addStringZips(Arrays.asList(zipCodes));
        }

        public Builder addZips(final ZipCode... zipCodes) {
            return addZips(Arrays.asList(zipCodes));
        }

        public Builder addStringZips(final Collection<String> zipCodes) {
            final List<ZipCode> transformedList = new ArrayList<ZipCode>(zipCodes.size());
            for (final String stringZip : zipCodes) {
                transformedList.add(ZipCode.parse(stringZip));
            }

            this.zipCodes.addAll(transformedList);
            return this;
        }

        public Builder addZips(final Collection<ZipCode> zipCodes) {
            this.zipCodes.addAll(zipCodes);
            return this;
        }

        public ZipCodeRouteRequest build() {
            return new ZipCodeRouteRequest(zipCodes);
        }
    }
}
