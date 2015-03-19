package com.lob.protocol;

import java.util.List;

public class ServiceCollection extends LobObject {
    List<Service> data;
    String object;
    
    public List<Service> getData() {
        return data;
    }
    public String getObject() {
        return object;
    }
}