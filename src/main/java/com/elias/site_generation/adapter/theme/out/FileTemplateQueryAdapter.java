package com.elias.site_generation.adapter.theme.out;

import com.elias.site_generation.adapter.file.out.FilePort;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.port.theme.TemplateQueryPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FileTemplateQueryAdapter implements TemplateQueryPort {

    private final FilePathProps props;
    private final FilePort filePort;

    @Override
    public boolean exists(TemplateType type) {
        FilePath filePath = FilePath.from(type.getName().concat(FileUtils.ZIP_FORMAT), props.getTemplates());
        return filePort.exists(filePath);
    }

}
