package com.lob.protocol.request;

import java.util.List;
import java.util.Map;

public interface ParamMappable {
    public Map<String, List<String>> toParamMap();
}
