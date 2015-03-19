package com.lob;

public final class Util {
    // prevent instantiation
    private Util() {}

    public static <T> T checkNotNull(final T ref) {
        if (ref == null) {
            throw new NullPointerException();
        }

        return ref;
    }

    public static String checkValidHex(final String s) {
        // handle the null and empty check right away
        if (checkNotNull(s).isEmpty()) {
            throw new IllegalArgumentException("string cannot be empty!");
        }

        try {
            Long.parseLong(s, 16);
        }
        catch (final NumberFormatException e) {
            throw new IllegalArgumentException("string " + s + " is not valid hex!");
        }

        return s;
    }
}
