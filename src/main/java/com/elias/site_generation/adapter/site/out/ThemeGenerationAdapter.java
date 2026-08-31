package com.elias.site_generation.adapter.site.out;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.generation.TemplateGenerationPort;
import com.elias.site_generation.adapter.theme.out.generation.TitleGenerationPort;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.exception.ThemeGenerationException;
import com.elias.site_generation.port.theme.ThemeGenerationPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
class ThemeGenerationAdapter implements ThemeGenerationPort {

    private final TitleGenerationPort titleGenerationPort;
    private final FilePathProps props;
    private final FileManagerPort fileManagerPort;
    private final TemplateGenerationPort templateGenerationPort;

    @Override
    public Theme generate(Site site) {
        log.info("Started generating theme.");
        String templateId = UUID.randomUUID().toString();

        try {
            return process(templateId, site);
        } catch (Exception e) {
            throw new ThemeGenerationException(site.getId(), "Unable to generate theme for site: %s.".formatted(site.getId()));
        }
    }

    private Theme process(String templateId, Site site) {
        String originalFilename = FileUtils.buildOriginalFilename(site.getType().getName(), FileUtils.ZIP_FORMAT);

        byte[] templateZip = fileManagerPort.read(FilePath.from(originalFilename, props.getTemplates()));
        String title = titleGenerationPort.generate();
        byte[] theme = generateTheme(title, site, templateZip);

        saveTheme(templateId, theme);
        return new Theme(templateId, title);
    }

    private byte[] generateTheme(String title, Site site, byte[] template) {
        ThemeGenerationRequest request = new ThemeGenerationRequest(title, site.getContent(), site.getLanguage(), template);
        return templateGenerationPort.generate(site.getType(), request);
    }

    private void saveTheme(String templateId, byte[] theme) {
        String originalFilename = FileUtils.buildOriginalFilename(templateId, FileUtils.ZIP_FORMAT);
        fileManagerPort.write(FilePath.from(originalFilename, props.getThemes()), theme);
    }
}