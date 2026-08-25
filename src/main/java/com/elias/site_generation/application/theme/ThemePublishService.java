package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Status;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.theme.ThemePublishUseCase;
import com.elias.site_generation.port.website.WebsiteThemePort;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import com.elias.site_generation.shared.utils.FuncUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ThemePublishService implements ThemePublishUseCase {

    @Value("${file.path.temp}")
    private String fileTemp;

    private final FilePathProps pathProps;

    private final HostingPort hostingPort;
    private final WebsiteThemePort websiteThemePort;
    private final RemoteCommandPort remoteCommandPort;

    @Override
    public void publish(long siteId, Theme theme) {
        FuncUtils.runOrThrow(hostingPort::createDomain, new ThemePublishingException(siteId, "Failed to select domain.", Status.DOMAIN_CREATION_FAILED));
        log.info("Selected domain for site: {}.", siteId);

        FuncUtils.runOrThrow(hostingPort::createDb, new ThemePublishingException(siteId, "Failed to create db.", Status.DB_CREATION_FAILED));
        log.info("Initialized db for site: {}.", siteId);

        FuncUtils.runOrThrow(websiteThemePort::downloadWebsite, new ThemePublishingException(siteId, "Failed to download WordPress.", Status.WEBSITE_DOWNLOAD_FAILED));
        log.info("Downloaded WordPress for site: {}.", siteId);

        FuncUtils.runOrThrow(websiteThemePort::createConfig, new ThemePublishingException(siteId, "Failed to configure WordPress.", Status.WEBSITE_CONFIGURATION_FAILED));
        log.info("Configured WordPress for site: {}.", siteId);

        FuncUtils.runOrThrow(websiteThemePort::installWebsite, new ThemePublishingException(siteId, "Failed to install WordPress.", Status.WEBSITE_INSTALLATION_FAILED));
        log.info("Installed WordPress for site: {}.", siteId);

        FuncUtils.runOrThrow(() -> installTheme(siteId, theme), new ThemePublishingException(siteId, "Failed to install theme.", Status.THEME_INSTALLATION_FAILED));
        log.info("Installed theme for site: {}.", siteId);
    }

    private void installTheme(long siteId, Theme theme) {
        String localFilepath = FileUtils.getFilePath(theme.id(), pathProps.getThemes());
        String tempPath = getDomainTempThemePath(siteId);

        System.out.println(tempPath);
        System.out.println(localFilepath);
        remoteCommandPort.upload(tempPath, localFilepath);
        System.out.println("created temp file");
        websiteThemePort.installTheme(FileUtils.getFilePath(theme.id(), pathProps.getThemes()));
        System.out.println("installed theme");
        remoteCommandPort.delete(tempPath);
        System.out.println("deleted theme");
    }

    private String getDomainTempThemePath(long siteId) {
        String originalFilename = FileUtils.buildOriginalFilename(String.valueOf(siteId), FileUtils.ZIP_FORMAT);
        return FileUtils.getTempPath(fileTemp, originalFilename);
    }
}
