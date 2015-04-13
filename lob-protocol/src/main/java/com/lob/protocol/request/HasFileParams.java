package com.lob.protocol.request;

import java.util.Collection;

public interface HasFileParams extends ParamMappable {
    public Collection<FileParam> getFileParams();
}
