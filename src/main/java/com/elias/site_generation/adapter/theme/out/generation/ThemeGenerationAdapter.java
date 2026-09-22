package com.elias.site_generation.adapter.theme.out.generation;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.file.FileManagerPort;
import com.elias.site_generation.adapter.theme.out.generation.factory.ThemeGenerationFactory;
import com.elias.site_generation.adapter.theme.out.generation.strategy.ThemeGenerationStrategy;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.exception.SiteGenerationException;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.port.theme.ThemeGenerationPort;
import com.elias.site_generation.port.title.TitleGenerationPort;
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
    private final ThemeImageGenerationPort themeImageGenerationPort;
    private final ThemeGenerationFactory generationFactory;

    @Override
    public String generate(String themeId, Site site) {
        log.info("Started generating theme.");

        try {
            String processed = process(themeId, site);
            log.info("Theme generated.");
            return processed;
        } catch (Exception e) {
            log.error("Exception occurred: {}.", e.getMessage(), e);
            throw new SiteGenerationException(site.getId(), "Unable to generate theme for site: %s.".formatted(site.getId()));
        }
    }

    private String process(String templateId, Site site) {
        String originalFilename = FileUtils.buildOriginalFilename(site.getType().getName(), FileUtils.ZIP_FORMAT);
        byte[] templateZip = fileManagerPort.read(FilePath.from(originalFilename, props.getTemplates()));

        String title = titleGenerationPort.generate(site.getContent());
        Map<String, byte[]> images = themeImageGenerationPort.generate(site.getContent(), templateZip);
        ThemeGenerationRequest request = new ThemeGenerationRequest(title, images, site.getContent(), site.getLanguage(), templateZip);

        saveTheme(templateId, processStrategy(site.getType(), request));
        return title;
    }

    private byte[] processStrategy(TemplateType type, ThemeGenerationRequest request) {
        ThemeGenerationStrategy strategy = generationFactory.getStrategy(type);
        return strategy.generate(request);
    }

    private void saveTheme(String templateId, byte[] theme) {
        String originalFilename = FileUtils.buildOriginalFilename(templateId, FileUtils.ZIP_FORMAT);
        fileManagerPort.write(FilePath.from(originalFilename, props.getThemes()), theme);
    }
}