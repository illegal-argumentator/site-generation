package com.elias.site_generation.adapter.file.out;

import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Component
class ZipFileAdapter implements ZipFilePort {

    @Override
    public byte[] update(byte[] source, Map<String, byte[]> files) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(source));
             ZipOutputStream zos = new ZipOutputStream(out)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                byte[] fileBytes = files.get(entry.getName());

                if (fileBytes == null) {
                    continue;
                }

                zos.putNextEntry(new ZipEntry(entry.getName()));
                zos.write(fileBytes);

                zos.closeEntry();
                zis.closeEntry();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return out.toByteArray();
    }

    @Override
    public Map<String, byte[]> extract(byte[] source) {
        Map<String, byte[]> files = new HashMap<>();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(source));
             ZipOutputStream zos = new ZipOutputStream(out)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                byte[] fileBytes = zis.readAllBytes();

                files.put(entry.getName(), fileBytes);
                zos.putNextEntry(new ZipEntry(entry.getName()));

                zos.closeEntry();
                zis.closeEntry();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return files;
    }
}
