package com.lob.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class SettingId extends IntegerId {
    public static SettingId BLACK_AND_WHITE_DOCUMENT = parse(100);
    public static SettingId COLOR_DOCUMENT = parse(101);
    public static SettingId COLOR_CARD_4X6 = parse(201);
    public static SettingId PREMIUM_COLOR_CARD_4X6 = parse(202);
    public static SettingId FOLDED_GREETING_CARD_5X7 = parse(203);
    public static SettingId SEMI_GLOSS_POSTER_8X10 = parse(304);
    public static SettingId SEMI_GLOSS_POSTER_11X17 = parse(305);
    public static SettingId SEMI_GLOSS_POSTER_18X24 = parse(306);
    public static SettingId SEMI_GLOSS_POSTER_24X36 = parse(307);
    public static SettingId BUSINESS_CARD_THICK_500_CT = parse(400);
    public static SettingId BUSINESS_CARD_STANDARD_250_CT = parse(402);
    public static SettingId GLOSS_PHOTO_4X6 = parse(500);
    public static SettingId GLOSS_PHOTO_5X7 = parse(501);
    public static SettingId GLOSS_PHOTO_8X10 = parse(502);
    public static SettingId GLOSS_PHOTO_4X4 = parse(503);
    public static SettingId GLOSS_PHOTO_8X8 = parse(504);
    public static SettingId STANDARD_DOOR_HANGERS_100_CT_4_25X11 = parse(800);
    public static SettingId GLOSSY_COLOR_FLYER_100_CT = parse(803);

    private SettingId(final int id) {
        super(id);
    }

    @JsonCreator
    public static SettingId parse(final String s) {
        return new SettingId(Integer.valueOf(s));
    }

    public static SettingId parse(final int settingId) {
        return new SettingId(settingId);
    }
}
