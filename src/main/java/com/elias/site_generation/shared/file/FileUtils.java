package com.elias.site_generation.shared.file;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    public static final String USER_DIR = "user.dir";

    public static final String ZIP_FORMAT = ".zip";

    public static String buildOriginalFilename(String name, String contentType) {
        return name.concat(contentType);
    }

}
