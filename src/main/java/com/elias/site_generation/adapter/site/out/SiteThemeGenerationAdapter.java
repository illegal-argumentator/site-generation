package com.elias.site_generation.adapter.site.out;

import com.elias.site_generation.adapter.file.out.FilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.TemplateGenerationPort;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.port.SiteThemeGenerationPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class SiteThemeGenerationAdapter implements SiteThemeGenerationPort {

    @Value("${file.path.themes}")
    private String THEMES_PATH;

    @Value("${file.path.templates}")
    private String TEMPLATES_PATH;

    private final FilePort filePort;
    private final TemplateGenerationPort templateGenerationPort;

    @Override
    public String generate(Site site) {
        String originalFilename = FileUtils.buildOriginalFilename(site.getType().getName(), FileUtils.ZIP_FORMAT);
        String templateId = UUID.randomUUID().toString();

        byte[] templateZip = filePort.read(FilePath.from(originalFilename, TEMPLATES_PATH));
        byte[] theme = generateTheme(site, templateZip);

        saveTheme(templateId, theme);

        return templateId;
    }

    private byte[] generateTheme(Site site, byte[] template) {
        ThemeGenerationRequest request = new ThemeGenerationRequest(site.getContent(), site.getLanguage(), template);
        return templateGenerationPort.generate(site.getType(), request);
    }

    private void saveTheme(String templateId, byte[] theme) {
        String originalFilename = FileUtils.buildOriginalFilename(templateId, FileUtils.ZIP_FORMAT);
        filePort.write(FilePath.from(originalFilename, THEMES_PATH), theme);
    }
}