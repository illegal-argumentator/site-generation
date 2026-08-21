package com.elias.site_generation.adapter.file.out;

import com.elias.site_generation.adapter.file.out.exception.FileReadException;
import com.elias.site_generation.adapter.file.out.exception.FileWriteException;
import com.elias.site_generation.adapter.site.out.FileManagerPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
class FileManagerAdapter implements FileManagerPort {

    @Override
    public void write(FilePath filePath, byte[] file) {
        try {
            Path path = getPath(filePath.filename(), filePath.directory());
            Files.createDirectories(path.getParent());
            Files.write(path, file);
        } catch (IOException e) {
            log.error("Unable to write file: {}.", e.getMessage());
            throw new FileWriteException("Unable to write file.");
        }
    }

    @Override
    public byte[] read(FilePath filePath) {
        try {
            return Files.readAllBytes(getPath(filePath.filename(), filePath.directory()));
        } catch (IOException e) {
            log.error("Unable to read file: {}.", e.getMessage());
            throw new FileReadException("Unable to read file.");
        }
    }

    @Override
    public boolean exists(FilePath filePath) {
        return Files.exists(getPath(filePath.filename(), filePath.directory()));
    }

    private Path getPath(String filename, String directory) {
        return Paths.get(
                System.getProperty(FileUtils.USER_DIR),
                directory,
                filename
        );
    }
}
