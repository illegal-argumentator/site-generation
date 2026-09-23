package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.adapter.theme.out.file.FileManagerPort;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.elias.site_generation.shared.utils.Delimiters.SLASH_DELIMITER;

@Component
@RequiredArgsConstructor
class WordPressTemplateSlugQueryAdapter implements WebsiteTemplateSlugQueryPort {

    private final FilePathProps props;
    private final FileManagerPort fileManagerPort;

    @Override
    public String getSlug(TemplateType type) {
        String originalFilename = FileUtils.buildOriginalFilename(type.getName(), FileUtils.ZIP_FORMAT);
        byte[] bytes = fileManagerPort.read(FilePath.from(originalFilename, props.getTemplates()));
        return retrieveSlug(bytes);
    }

    private String retrieveSlug(byte[] file) {
        try (ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(file))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();

                int slashIndex = name.indexOf(SLASH_DELIMITER);

                if (slashIndex > 0) {
                    System.out.println("Slug name: " + name);
                    System.out.println("Slug: " + name.substring(0, slashIndex));
                    return name.substring(0, slashIndex);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read template archive", e);
        }

        throw new IllegalStateException("Template archive does not contain a root folder");
    }
}
