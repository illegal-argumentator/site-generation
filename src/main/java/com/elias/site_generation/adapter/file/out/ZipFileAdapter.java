package com.elias.site_generation.adapter.file.out;

import com.elias.site_generation.adapter.file.out.exception.FileReadException;
import com.elias.site_generation.adapter.file.out.exception.FileWriteException;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
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

    public byte[] update(byte[] target, Map<String, byte[]> files) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(target));
             ZipOutputStream zos = new ZipOutputStream(out)) {

            ZipEntry entry;
            Set<String> existingFiles = new HashSet<>();

            while ((entry = zis.getNextEntry()) != null) {
                String filename = entry.getName();
                existingFiles.add(filename);

                zos.putNextEntry(new ZipEntry(filename));
                byte[] fileEntry = files.get(filename);

                if (fileEntry != null) {
                    zos.write(fileEntry);
                } else {
                    zis.transferTo(zos);
                }

                writeNotExisting(existingFiles, zos, files);
                zos.closeEntry();
                zis.closeEntry();
            }

        } catch (IOException e) {
            log.error("Unable to write file: {}.", e.getMessage());
            throw new FileWriteException("Unable to write file.");
        }

        return out.toByteArray();
    }

    private void writeNotExisting(Set<String> existingFiles, ZipOutputStream zos, Map<String, byte[]> files) throws IOException {
        for (Map.Entry<String, byte[]> file : files.entrySet()) {
            if (!existingFiles.contains(file.getKey())) {
                zos.putNextEntry(new ZipEntry(file.getKey()));
                zos.write(file.getValue());
                zos.closeEntry();
            }
        }
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
}
