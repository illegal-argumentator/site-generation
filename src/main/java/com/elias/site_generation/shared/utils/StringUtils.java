package com.elias.site_generation.shared.utils;

public final class StringUtils {

    public static final String EMPTY_STRING = "";

    private StringUtils() {}

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String defaultIfEmpty(String str, String defaultStr) {
        return isEmpty(str) ? defaultStr : str;
    }

}
