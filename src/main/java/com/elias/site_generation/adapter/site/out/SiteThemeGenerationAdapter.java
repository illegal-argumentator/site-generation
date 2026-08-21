package com.elias.site_generation.adapter.site.out;

import com.elias.site_generation.adapter.file.out.FilePort;
import com.elias.site_generation.adapter.file.out.exception.FileReadException;
import com.elias.site_generation.adapter.file.out.exception.FileWriteException;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.TemplateGenerationPort;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.port.SiteThemeGenerationPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import com.elias.site_generation.shared.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
class SiteThemeGenerationAdapter implements SiteThemeGenerationPort {

    private final FilePathProps props;
    private final FilePort filePort;
    private final TemplateGenerationPort templateGenerationPort;

    @Override
    public ResponseBody<String> generate(Site site) {
        String templateId = UUID.randomUUID().toString();

        try {
            process(templateId, site);
            return ResponseBody.from(templateId);
        } catch (FileReadException e) {
            return ResponseBody.fail(HttpStatus.BAD_REQUEST.value(), "Unable to find requested template: %s.".formatted(site.getThemeId()));
        } catch (FileWriteException e) {
            return ResponseBody.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to save new theme: %s.".formatted(templateId));
        } catch (Exception e) {
            return ResponseBody.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }

    private void process(String templateId, Site site) {
        String originalFilename = FileUtils.buildOriginalFilename(site.getType().getName(), FileUtils.ZIP_FORMAT);

        byte[] templateZip = filePort.read(FilePath.from(originalFilename, props.getTemplates()));
        byte[] theme = generateTheme(site, templateZip);

        saveTheme(templateId, theme);
    }

    private byte[] generateTheme(Site site, byte[] template) {
        ThemeGenerationRequest request = new ThemeGenerationRequest(site.getContent(), site.getLanguage(), template);
        return templateGenerationPort.generate(site.getType(), request);
    }

    private void saveTheme(String templateId, byte[] theme) {
        String originalFilename = FileUtils.buildOriginalFilename(templateId, FileUtils.ZIP_FORMAT);
        filePort.write(FilePath.from(originalFilename, props.getThemes()), theme);
    }
}