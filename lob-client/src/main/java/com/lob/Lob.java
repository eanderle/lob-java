package com.lob;

public final class Lob {
    public static final String API_2014_12_18 = "2014-12-18";

    private static String BASE_URL = "https://api.lob.com/v1/";
    private static String API_VERSION = API_2014_12_18;

    // prevent instantiation
    private Lob() {}

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static void setBaseUrl(final String baseUrl) {
        BASE_URL = baseUrl;
    }

    public static String getApiVersion() {
        return API_VERSION;
    }

    public static void setApiVersion(final String apiVersion) {
        API_VERSION = apiVersion;
    }
}
