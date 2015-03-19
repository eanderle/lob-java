package com.lob.protocol;

import java.lang.*;
import java.util.List;

public class ObjectCollection extends LobObject {
    List<com.lob.protocol.response.LobObject> data;
    String object;

    public List<com.lob.protocol.response.LobObject> getData() {
        return data;
    }
    public String getObject() {
        return object;
    }
}