package com.lob.protocol.response;

public interface RequestTransformer<T> {

    public T toRequest();
}
