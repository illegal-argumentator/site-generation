package com.elias.site_generation.adapter.theme.out.generation;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.theme.out.file.FileManagerPort;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.out.prompt.ThemePromptPolicyBuilder;
import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.port.theme.ThemeEditPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
class ThemeEditAdapter implements ThemeEditPort {

    private final AiService aiService;

    private final FilePathProps props;
    private final FileManagerPort fileManagerPort;

    private final ZipFilePort zipFilePort;

    @Override
    public void process(String content, TemplateComponent component, Site site) {
        byte[] oldTheme = getTheme(site.getTheme().id());
        removeOldTheme(site.getTheme().id());

        String editedComponent = editComponent(content, component, oldTheme);

        byte[] updatedTheme = zipFilePort.update(oldTheme, Map.of(component.getName(), editedComponent.getBytes(StandardCharsets.UTF_8)));
        saveTheme(site.getTheme().id(), updatedTheme);
    }

    private void removeOldTheme(String themeId) {
        String originalFilename = FileUtils.buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        fileManagerPort.remove(FilePath.from(originalFilename, props.getThemes()));
    }

    private String editComponent(String content, TemplateComponent component, byte[] oldTheme) {
        AiRequest aiRequest = new AiRequest(ThemePromptPolicyBuilder.buildCustomEditPrompt(content, getThemeComponent(component, oldTheme)), content);
        return aiService.generate(aiRequest);
    }

    private byte[] getTheme(String themeId) {
        String originalFilename = FileUtils.buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        return fileManagerPort.read(FilePath.from(originalFilename, props.getThemes()));
    }

    private String getThemeComponent(TemplateComponent component, byte[] theme) {
        byte[] themeComp = zipFilePort.extract(component.getName(), theme);
        return new String(themeComp);
    }

    private void saveTheme(String themeId, byte[] theme) {
        String originalFilename = FileUtils.buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        fileManagerPort.write(FilePath.from(originalFilename, props.getThemes()), theme);
    }
}
