package com.elias.site_generation.adapter.theme.out;

import com.elias.site_generation.adapter.theme.out.file.FileManagerPort;
import com.elias.site_generation.port.theme.ThemeDeletionPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ThemeDeletionAdapter implements ThemeDeletionPort {

    private final FilePathProps props;
    private final FileManagerPort fileManagerPort;

    @Override
    public void delete(String themeId) {
        String originalFilename = FileUtils.buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        fileManagerPort.remove(FilePath.from(originalFilename, props.getThemes()));
    }

}
