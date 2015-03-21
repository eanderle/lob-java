package com.lob.id;

import com.fasterxml.jackson.annotation.JsonValue;

public class SettingId implements IntegerId {
    public static SettingId BLACK_AND_WHITE_DOCUMENT = create(100);
    public static SettingId COLOR_DOCUMENT = create(101);
    public static SettingId COLOR_CARD_4X6 = create(201);
    public static SettingId PREMIUM_COLOR_CARD_4X6 = create(202);
    public static SettingId FOLDED_GREETING_CARD_5X7 = create(203);
    public static SettingId SEMI_GLOSS_POSTER_8X10 = create(304);
    public static SettingId SEMI_GLOSS_POSTER_11X17 = create(305);
    public static SettingId SEMI_GLOSS_POSTER_18X24 = create(306);
    public static SettingId SEMI_GLOSS_POSTER_24X36 = create(307);
    public static SettingId BUSINESS_CARD_THICK_500_CT = create(400);
    public static SettingId BUSINESS_CARD_STANDARD_250_CT = create(402);
    public static SettingId GLOSS_PHOTO_5X7 = create(501);
    public static SettingId GLOSS_PHOTO_8X10 = create(502);
    public static SettingId GLOSS_PHOTO_4X4 = create(503);
    public static SettingId GLOSS_PHOTO_8X8 = create(504);
    public static SettingId STANDARD_DOOR_HANGERS_100_CT_4_25X11 = create(800);
    public static SettingId GLOSSY_COLOR_FLYER_100_CT = create(803);

    private final int id;

    public SettingId(final int id) {
        this.id = id;
    }

    public static SettingId create(final int settingId) {
        return new SettingId(settingId);
    }

    @Override
    public int getId() {
        return this.id;
    }
}
