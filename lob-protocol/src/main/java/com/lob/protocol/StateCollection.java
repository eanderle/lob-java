package com.lob.protocol;

import java.util.List;

public class StateCollection extends LobObject {
    List<State> data;
    String object;

    public List<State> getData() {
        return data;
    }
    public String getObject() {
        return object;
    }
}