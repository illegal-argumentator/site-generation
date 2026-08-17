package com.elias.site_generation.adapter.file.out;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
class DefaultFileService implements FileService {

    @Value("${themes.path}")
    private String THEMES_PATH;

    @Override
    public void write(String name, byte[] file) {
        try {
            Files.write(Path.of(THEMES_PATH, name), file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] read(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
