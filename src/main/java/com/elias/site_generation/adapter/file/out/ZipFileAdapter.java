package com.elias.site_generation.adapter.file.out;

import com.elias.site_generation.adapter.file.out.exception.FileReadException;
import com.elias.site_generation.adapter.file.out.exception.FileWriteException;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.shared.file.FilePath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Slf4j
@Component
class ZipFileAdapter implements ZipFilePort {

    @Override
    public byte[] update(byte[] target, Map<FilePath, byte[]> files) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(target));
             ZipOutputStream zos = new ZipOutputStream(out)) {

            ZipEntry entry;
            Set<String> existingEntries = new HashSet<>();

            while ((entry = zis.getNextEntry()) != null) {
                zos.putNextEntry(new ZipEntry(entry.getName()));
                existingEntries.add(entry.getName());

                byte[] fileEntry = getFileEntry(entry.getName(), files);
                if (fileEntry != null) {
                    zos.write(fileEntry);
                } else {
                    zis.transferTo(zos);
                }

                zos.closeEntry();
                zis.closeEntry();
            }

            for (Map.Entry<FilePath, byte[]> file : files.entrySet()) {
                FilePath filePath = file.getKey();
                if (containsByText(filePath.filename(), existingEntries)) {
                    continue;
                }

                zos.putNextEntry(new ZipEntry(filePath.buildPath()));
                zos.write(file.getValue());
                zos.closeEntry();
            }

        } catch (IOException e) {
            log.error("Unable to write file: {}.", e.getMessage());
            throw new FileWriteException("Unable to write file.");
        }

        return out.toByteArray();
    }

    @Override
    public byte[] extract(String filename, byte[] source) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(source));
             ZipOutputStream zos = new ZipOutputStream(out)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                byte[] fileBytes = zis.readAllBytes();

                if (entry.getName().contains(filename)) {
                    if (fileBytes == null) {
                        throw new FileReadException("Unable to read file: %s because it's empty.".formatted(filename));
                    }

                    return fileBytes;
                }

                zos.putNextEntry(new ZipEntry(entry.getName()));
                zos.closeEntry();
                zis.closeEntry();
            }

        } catch (IOException e) {
            throw new FileReadException("Unable to read file: %s.".formatted(filename));
        }

        throw new FileReadException("File %s not found.".formatted(filename));
    }

    private byte[] getFileEntry(String filename, Map<FilePath, byte[]> files) {
        for (Map.Entry<FilePath, byte[]> entry : files.entrySet()) {
            if (filename.contains(entry.getKey().filename())) {
                return entry.getValue();
            }
        }

        return null;
    }

    private boolean containsByText(String target,  Set<String> existingEntries) {
        for (String existingEntry : existingEntries) {
            if (existingEntry.contains(target)) return true;
        }

        return false;
    }

}
