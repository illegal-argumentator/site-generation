package com.elias.site_generation.shared.file;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileUtils {

    public static final String USER_DIR = "user.dir";
    public static final String ZIP_FORMAT = ".zip";
    public static final String TEMP_PATH = "/tmp/";

    public static String buildOriginalFilename(String name, String contentType) {
        if (name == null || contentType == null) {
            throw new IllegalArgumentException("Name or ContentType is null.");
        }

        return name.concat(contentType);
    }

    public static String getFilePath(String themeId, String directory) {
        String originalFilename = buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        Path absolutePath = getAbsolutePath(directory);
        return absolutePath.resolve(originalFilename).toString();
    }

    public static String getTempPath(String folder, String filename) {
        return TEMP_PATH + folder + filename;
    }

    private static Path getAbsolutePath(String directory) {
        if (directory == null || directory.isBlank()) {
            throw new IllegalArgumentException("Directory is empty.");
        }

        return Paths.get(directory).toAbsolutePath().normalize();
    }

}
