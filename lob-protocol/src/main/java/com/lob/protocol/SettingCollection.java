package com.lob.protocol;

import java.util.List;

public class SettingCollection extends LobObject {
    List<Setting> data;
    String object;
    
    public List<Setting> getData() {
        return data;
    }
    public String getObject() {
        return object;
    }
}