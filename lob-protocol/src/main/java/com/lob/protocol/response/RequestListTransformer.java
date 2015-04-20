package com.lob.protocol.response;

import java.util.Collection;

public interface RequestListTransformer<T> {

    public Collection<T> toRequest();
}
