package com.lob.protocol;

import com.lob.protocol.request.LobObjectRequest;

import java.lang.*;
import java.util.List;

public class ObjectCollection extends LobObject {
    List<LobObjectRequest> data;
    String object;

    public List<LobObjectRequest> getData() {
        return data;
    }
    public String getObject() {
        return object;
    }
}