package com.elias.site_generation.adapter.file.out;

import com.elias.site_generation.adapter.file.out.exception.FileReadException;
import com.elias.site_generation.adapter.file.out.exception.FileWriteException;
import com.elias.site_generation.adapter.theme.out.file.FileManagerPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.utils.AppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
class FileManagerAdapter implements FileManagerPort {

    @Override
    public void write(FilePath filePath, byte[] file) {
        try {
            Path path = FileUtils.getPath(filePath.filename(), AppUtils.getFilePrefixByOs() + filePath.directory());
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
            return Files.readAllBytes(FileUtils.getPath(filePath.filename(), AppUtils.getFilePrefixByOs() + filePath.directory()));
        } catch (IOException e) {
            log.error("Unable to read file: {}.", e.getMessage());
            throw new FileReadException("Unable to read file.");
        }
    }

    @Override
    public boolean exists(FilePath filePath) {
        return Files.exists(FileUtils.getPath(filePath.filename(), AppUtils.getFilePrefixByOs() + filePath.directory()));
    }

    @Override
    public void remove(FilePath filePath) {
        if (!exists(filePath)) return;

        try {
            Path path = FileUtils.getPath(filePath.filename(), AppUtils.getFilePrefixByOs() + filePath.directory());
            Files.delete(path);
        } catch (IOException e) {
            log.error("Unable to remove file: {}.", e.getMessage());
            throw new FileWriteException("Unable to remove file.");
        }
    }
}
