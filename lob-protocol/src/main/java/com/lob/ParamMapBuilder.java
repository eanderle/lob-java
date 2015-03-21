package com.lob;

import com.lob.id.IntegerId;
import com.lob.id.LobId;
import com.lob.protocol.request.ParamMappable;
import com.neovisionaries.i18n.CountryCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamMapBuilder {
    private final Map<String, List<String>> map = new HashMap<String, List<String>>();

    public static ParamMapBuilder create() {
        return new ParamMapBuilder();
    }

    public ParamMapBuilder put(final String k, final String v) {
        if (v == null) {
            return this;
        }

        putAll(k, Arrays.asList(v));
        return this;
    }

    public ParamMapBuilder put(final String k, final int v) {
        return put(k, Integer.toString(v));
    }

    public ParamMapBuilder put(final String k, final boolean v) {
        return put(k, (v ? "1" : "0"));
    }

    public ParamMapBuilder put(final String k, final Or<? extends LobId, ? extends ParamMappable> v) {
        if (v == null) {
            return this;
        }

        if (v.isTypeA()) {
            return put(k, v.getTypeA().value());
        }
        else {
            return put(k, v.getTypeB());
        }
    }

    public ParamMapBuilder put(final String k, final OrCollection<? extends LobId, ? extends ParamMappable> v) {
        if (v == null) {
            return this;
        }

        int i = 1;
        if (v.isTypeA()) {
            final Collection<? extends LobId> ids = v.getTypeA();
            for (final LobId id : ids) {
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

    public ParamMapBuilder put(final String k, final CountryCode code) {
        if (code == null) {
            return this;
        }
        return put(k, code.getAlpha2());
    }

    public ParamMapBuilder put(final String k, final IntegerId id) {
        if (id == null) {
            return this;
        }
        return put(k, id.getId());
    }

    public ParamMapBuilder put(final String k, final ParamMappable object) {
        if (object == null) {
            return this;
        }

        for (final Map.Entry<String, List<String>> entry : object.toParamMap().entrySet()) {
            // XXX not sure how it's going to work if we get more than one level deep
            putAll(k + "[" + entry.getKey() + "]", entry.getValue());
        }
        return this;
    }

    public ParamMapBuilder putAll(final String k, final List<String> v) {
        if (v == null || v.isEmpty()) {
            return this;
        }

        List<String> vList = this.map.get(k);
        if (vList == null) {
            vList = new ArrayList<String>();
            this.map.put(k, vList);
        }

        vList.addAll(v);
        return this;
    }

    public Map<String, List<String>> build() {
        return this.map;
    }
}
