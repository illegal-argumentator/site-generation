package com.elias.site_generation.shared.utils;

import static com.elias.site_generation.shared.utils.Delimiters.SLASH_PREFIX;
import static com.elias.site_generation.shared.utils.StringUtils.EMPTY_STRING;

public class AppUtils {

    private static final String UNNAMED = "unnamed", OS_NAME = "os.name", WINDOW = "window";

    private AppUtils() {
    }

    public static boolean isWindows() {
        return System.getProperty(OS_NAME).toLowerCase().contains(WINDOW);
    }

    public static String getFilePrefixByOs() {
        return isWindows() ? EMPTY_STRING : SLASH_PREFIX;
    }

    public static String toSafeFileName(String input) {
        if (input == null || input.isBlank()) {
            return UNNAMED;
        }

        String result = input
                .replaceAll("[\\\\/:*?\"<>|]", "")
                .replaceAll("[\\x00-\\x1F]", "")
                .replaceAll("\\s+", "")
                .replaceAll("[. ]+$", "");

        return result.isBlank() ? UNNAMED : result;
    }

}

