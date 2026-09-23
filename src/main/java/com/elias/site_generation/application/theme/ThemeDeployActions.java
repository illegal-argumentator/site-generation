package com.elias.site_generation.application.theme;

import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
final class ThemeDeployActions {

    @Value("${file.path.temp}")
    private String fileTemp;

    private final FilePathProps pathProps;
    private final RemoteCommandPort remoteCommandPort;
    private final WebsiteThemeCommandPort websiteThemeCommandPort;

    void installTheme(String hostname, String themeId) {
        String localFilepath = FileUtils.getFilePath(themeId, pathProps.getThemes());
        String tempPath = getDomainTempThemePath(themeId);

        remoteCommandPort.upload(localFilepath, tempPath);
        websiteThemeCommandPort.installTheme(tempPath, hostname);
        remoteCommandPort.delete(tempPath);
    }

    private String getDomainTempThemePath(String themeId) {
        String originalFilename = FileUtils.buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        return FileUtils.getTempPath(fileTemp, originalFilename);
    }

}
