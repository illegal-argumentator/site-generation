package com.elias.site_generation.shared.file;

public record FilePath(String filename, String directory) {

    public static FilePath from(String filename, String directory) {
        return new FilePath(filename, directory);
    }

}
