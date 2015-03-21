package com.lob.protocol.response;

import com.lob.id.SettingId;

public class SettingResponse {
	private final SettingId id;
	private final String type;
	private final String description;
	private final String paper;
	private final String width;
	private final String length;
	private final String color;
	private final String notes;
	private final String object;

    public SettingResponse(
            final SettingId id,
            final String type,
            final String description,
            final String paper,
            final String width,
            final String length,
            final String color,
            final String notes,
            final String object) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.paper = paper;
        this.width = width;
        this.length = length;
        this.color = color;
        this.notes = notes;
        this.object = object;
    }

    public SettingId getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getPaper() {
        return paper;
    }

    public String getWidth() {
        return width;
    }

    public String getLength() {
        return length;
    }

    public String getColor() {
        return color;
    }

    public String getNotes() {
        return notes;
    }

    public String getObject() {
        return object;
    }
}
