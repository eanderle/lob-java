package com.lob.protocol.request;

import java.util.Collection;
import java.util.Map;

public interface ParamMappable {
    public Map<String, Collection<String>> toParamMap();
}
