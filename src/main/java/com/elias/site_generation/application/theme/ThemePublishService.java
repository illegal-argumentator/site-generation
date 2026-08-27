package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.Status;
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
    public void publish(Site site, Theme theme) {
        Long id = site.getId();

        FuncUtils.runOrThrow(() -> hostingPort.createDomain(site.getHostname()), new ThemePublishingException(id, "Failed to select domain.", Status.DOMAIN_CREATION_FAILED));
        log.info("Selected domain for site: {}.", id);

        FuncUtils.runOrThrow(() -> hostingPort.enableSsl(site.getHostname()), new ThemePublishingException(id, "Failed to enable ssl for domain.", Status.SSL_ENABLE_FAILED));
        log.info("Enabled ssl for domain: {}.", site.getHostname());

        FuncUtils.runOrThrow(() -> hostingPort.createDb(site.getDbName(), site.getDbPass()), new ThemePublishingException(id, "Failed to create db.", Status.DB_CREATION_FAILED));
        log.info("Initialized db for site: {}.", id);

        FuncUtils.runOrThrow(websiteThemePort::downloadWebsite, new ThemePublishingException(id, "Failed to download WordPress.", Status.WEBSITE_DOWNLOAD_FAILED));
        log.info("Downloaded WordPress for site: {}.", id);

        FuncUtils.runOrThrow(() -> websiteThemePort.createConfig(site.getDbName(), site.getDbPass()), new ThemePublishingException(id, "Failed to configure WordPress.", Status.WEBSITE_CONFIGURATION_FAILED));
        log.info("Configured WordPress for site: {}.", id);

        FuncUtils.runOrThrow(() -> websiteThemePort.installWebsite(site.getHostname()), new ThemePublishingException(id, "Failed to install WordPress.", Status.WEBSITE_INSTALLATION_FAILED));
        log.info("Installed WordPress for site: {}.", id);

        FuncUtils.runOrThrow(() -> installTheme(theme), new ThemePublishingException(id, "Failed to install theme.", Status.THEME_INSTALLATION_FAILED));
        log.info("Installed theme for site: {}.", id);
    }

    private void installTheme(Theme theme) {
        String localFilepath = FileUtils.getFilePath(theme.id(), pathProps.getThemes());
        String tempPath = getDomainTempThemePath(theme.id());

        remoteCommandPort.upload(localFilepath, tempPath);
        websiteThemePort.installTheme(tempPath);
        remoteCommandPort.delete(tempPath);
    }

    private String getDomainTempThemePath(String themeId) {
        String originalFilename = FileUtils.buildOriginalFilename(themeId, FileUtils.ZIP_FORMAT);
        return FileUtils.getTempPath(fileTemp, originalFilename);
    }
}
