package com.lob.protocol.response;

import com.lob.id.LobObjectId;
import com.lob.protocol.Setting;

public class LobObject {
    private final LobObjectId id;
    private final String name;
    private final String file;
    private final Setting setting;
    private final int quantity;
    private final int doubleSided;

    public LobObject(
            final LobObjectId id,
            final String name,
            final String file,
            final Setting setting,
            final int quantity,
            final int doubleSided) {
        this.id = id;
        this.name = name;
        this.file = file;
        this.setting = setting;
        this.quantity = quantity;
        this.doubleSided = doubleSided;
    }
}
