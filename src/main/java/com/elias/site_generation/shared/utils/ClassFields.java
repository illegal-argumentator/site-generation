package com.elias.site_generation.shared.utils;

import java.util.Arrays;

public final class ClassFields {

    private ClassFields() {}

    public static String[] getEnumValues(Class<?> clazz) {
        if (clazz == null || !clazz.isEnum()) {
            return new String[0];
        }
        return Arrays.stream(clazz.getEnumConstants())
                .map(Object::toString)
                .toArray(String[]::new);
    }
}
