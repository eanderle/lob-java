package com.lob.protocol.request;

import java.util.Collection;
import java.util.Map;

public interface HasFileParams extends ParamMappable {
    public Map<String, Collection<String>> toParamMapWithFiles();

    public boolean isRequestWithFile();

    public Collection<FileParam> getFileParams();
}
