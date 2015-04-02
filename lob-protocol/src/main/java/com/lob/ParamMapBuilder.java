package com.lob;

import com.lob.id.StringValued;
import com.lob.protocol.request.ParamMappable;
import com.neovisionaries.i18n.CountryCode;
import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParamMapBuilder {
    private final static MoneyFormatter MONEY_FORMAT = new MoneyFormatterBuilder().appendAmount().toFormatter();

    private final Map<String, Collection<String>> map = new HashMap<String, Collection<String>>();

    public static ParamMapBuilder create() {
        return new ParamMapBuilder();
    }

    public ParamMapBuilder put(final String k, final String v) {
        if (v == null) {
            return this;
        }

        putAllStrings(k, Arrays.asList(v));
        return this;
    }

    public ParamMapBuilder put(final String k, final Integer v) {
        if (v == null) {
            return this;
        }

        return put(k, Integer.toString(v));
    }

    public ParamMapBuilder put(final String k, final Boolean v) {
        if (v == null) {
            return this;
        }

        return put(k, (v ? "1" : "0"));
    }

    public ParamMapBuilder put(final String k, final StringValued v) {
        if (v == null) {
            return this;
        }

        return put(k, v.value());
    }

    public ParamMapBuilder put(final String k, final Or<? extends StringValued, ? extends ParamMappable> v) {
        if (v == null) {
            return this;
        }

        return (v.isTypeA() ? put(k, v.getTypeA()) : put(k, v.getTypeB()));
    }

    public ParamMapBuilder put(final String k, final Money v) {
        if (v == null) {
            return this;
        }

        return put(k, MONEY_FORMAT.print(v));
    }

    public ParamMapBuilder put(final String k, final CountryCode code) {
        if (code == null) {
            return this;
        }
        return put(k, code.getAlpha2());
    }

    public ParamMapBuilder put(final String k, final ParamMappable object) {
        if (object == null) {
            return this;
        }

        for (final Map.Entry<String, Collection<String>> entry : object.toParamMap().entrySet()) {
            // XXX not sure how it's going to work if we get more than one level deep
            putAllStrings(k + "[" + entry.getKey() + "]", entry.getValue());
        }
        return this;
    }

    public ParamMapBuilder putAll(final String k, final OrCollection<? extends StringValued, ? extends ParamMappable> v) {
        if (v == null) {
            return this;
        }

        int i = 1;
        if (v.isTypeA()) {
            final Collection<? extends StringValued> ids = v.getTypeA();
            for (final StringValued id : ids) {
                put(k + i++, id.value());
            }
        }
        else {
            final Collection<? extends ParamMappable> objects = v.getTypeB();
            for (final ParamMappable object : objects) {
                put(k + i++, object);
            }
        }
        return this;
    }

    public ParamMapBuilder putAllStringValued(final String k, final OrCollection<? extends StringValued, ? extends StringValued> v) {
        if (v == null) {
            return this;
        }

        return (v.isTypeA() ? putAll(k, v.getTypeA()) : putAll(k, v.getTypeB()));
    }

    public ParamMapBuilder putAll(final String k, final Collection<? extends StringValued> v) {
        if (v == null) {
            return this;
        }

        for (final StringValued s : v) {
            put(k, s.value());
        }
        return this;
    }

    public ParamMapBuilder putAllStrings(final String k, final Collection<String> v) {
        if (v == null || v.isEmpty()) {
            return this;
        }

        Collection<String> vList = this.map.get(k);
        if (vList == null) {
            vList = new ArrayList<String>();
            this.map.put(k, vList);
        }

        vList.addAll(v);
        return this;
    }

    public Map<String, Collection<String>> build() {
        return this.map;
    }
}
