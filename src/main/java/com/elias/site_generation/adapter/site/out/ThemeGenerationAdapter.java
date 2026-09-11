package com.elias.site_generation.adapter.site.out;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.generation.TemplateGenerationPort;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteGenerationException;
import com.elias.site_generation.port.theme.ImageGenerationPort;
import com.elias.site_generation.port.theme.ThemeGenerationPort;
import com.elias.site_generation.port.theme.TitleGenerationPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
class ThemeGenerationAdapter implements ThemeGenerationPort {

    private final FilePathProps props;
    private final FileManagerPort fileManagerPort;
    private final TitleGenerationPort titleGenerationPort;
    private final ImageGenerationPort imageGenerationPort;
    private final TemplateGenerationPort templateGenerationPort;

    @Override
    public String generate(String themeId, Site site) {
        log.info("Started generating theme.");

        try {
            String processed = process(themeId, site);
            log.info("Theme generated.");
            return processed;
        } catch (Exception e) {
            log.error("Exception occurred: {}.", e.getMessage());
            throw new SiteGenerationException(site.getId(), "Unable to generate theme for site: %s.".formatted(site.getId()));
        }
    }

    private String process(String templateId, Site site) {
        String originalFilename = FileUtils.buildOriginalFilename(site.getType().getName(), FileUtils.ZIP_FORMAT);
        byte[] templateZip = fileManagerPort.read(FilePath.from(originalFilename, props.getTemplates()));

        String title = titleGenerationPort.generate();
        Map<String, byte[]> images = imageGenerationPort.generate(templateZip);
        ThemeGenerationRequest request = new ThemeGenerationRequest(title, images, site.getContent(), site.getLanguage(), templateZip);
        byte[] generated = templateGenerationPort.generate(site.getType(), request);

        saveTheme(templateId, generated);
        return title;
    }

    private void saveTheme(String templateId, byte[] theme) {
        String originalFilename = FileUtils.buildOriginalFilename(templateId, FileUtils.ZIP_FORMAT);
        fileManagerPort.write(FilePath.from(originalFilename, props.getThemes()), theme);
    }
}