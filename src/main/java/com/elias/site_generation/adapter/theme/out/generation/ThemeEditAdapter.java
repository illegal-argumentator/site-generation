package com.elias.site_generation.adapter.theme.out.generation;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.theme.out.file.FileManagerPort;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.out.prompt.ThemePromptPolicyBuilder;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.port.theme.ThemeEditPort;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
@RequiredArgsConstructor
class ThemeEditAdapter implements ThemeEditPort {

    private final AiService aiService;

    private final FilePathProps props;
    private final FileManagerPort fileManagerPort;

    private final ZipFilePort zipFilePort;

    @Override
    public byte[] edit(String content, TemplateComponent component, Site site) {
        byte[] theme = getTheme(site.getId());

        AiRequest aiRequest = new AiRequest(ThemePromptPolicyBuilder.buildCustomEditPrompt(content, getThemeComponent(component, theme)), content);
        String editedComponent = aiService.generate(aiRequest);

        return zipFilePort.update(theme, Map.of(component.getName(), editedComponent.getBytes(StandardCharsets.UTF_8)));
    }

    private byte[] getTheme(long siteId) {
        String originalFilename = FileUtils.buildOriginalFilename(String.valueOf(siteId), FileUtils.ZIP_FORMAT);
        return fileManagerPort.read(FilePath.from(originalFilename, props.getThemes()));
    }

    private String getThemeComponent(TemplateComponent component, byte[] theme) {
        byte[] themeComp = zipFilePort.extract(component.getName(), theme);
        return new String(themeComp);
    }


}
