package com.lob.protocol.request;

import com.fasterxml.jackson.annotation.JsonValue;

public class SettingRequest {
    public static SettingRequest BLACK_AND_WHITE_DOCUMENT = create(100);
    public static SettingRequest COLOR_DOCUMENT = create(101);
    public static SettingRequest COLOR_CARD_4X6 = create(201);
    public static SettingRequest PREMIUM_COLOR_CARD_4X6 = create(202);
    public static SettingRequest FOLDED_GREETING_CARD_5X7 = create(203);
    public static SettingRequest SEMI_GLOSS_POSTER_8X10 = create(304);
    public static SettingRequest SEMI_GLOSS_POSTER_11X17 = create(305);
    public static SettingRequest SEMI_GLOSS_POSTER_18X24 = create(306);
    public static SettingRequest SEMI_GLOSS_POSTER_24X36 = create(307);
    public static SettingRequest BUSINESS_CARD_THICK_500_CT = create(400);
    public static SettingRequest BUSINESS_CARD_STANDARD_250_CT = create(402);
    public static SettingRequest GLOSS_PHOTO_5X7 = create(501);
    public static SettingRequest GLOSS_PHOTO_8X10 = create(502);
    public static SettingRequest GLOSS_PHOTO_4X4 = create(503);
    public static SettingRequest GLOSS_PHOTO_8X8 = create(504);
    public static SettingRequest STANDARD_DOOR_HANGERS_100_CT_4_25X11 = create(800);
    public static SettingRequest GLOSSY_COLOR_FLYER_100_CT = create(803);

    private final int id;

    public SettingRequest(final int id) {
        this.id = id;
    }

    public static SettingRequest create(final int settingId) {
        return new SettingRequest(settingId);
    }

    @JsonValue
    public int getId() {
        return this.id;
    }
}
