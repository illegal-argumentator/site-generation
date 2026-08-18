package com.elias.site_generation.adapter.theme.out;

import com.elias.site_generation.adapter.file.out.FilePort;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.port.theme.ThemeQueryPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileThemeQueryAdapter implements ThemeQueryPort {

    @Value("${file.path.templates}")
    private String TEMPLATES_PATH;

    private final FilePort filePort;

    @Override
    public boolean exists(TemplateType type) {
        FilePath filePath = FilePath.from(type.getName().concat(FileUtils.ZIP_FORMAT), TEMPLATES_PATH);
        return filePort.exists(filePath);
    }

}
