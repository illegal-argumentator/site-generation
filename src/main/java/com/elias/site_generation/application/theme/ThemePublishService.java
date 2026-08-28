package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.Status;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.theme.ThemePublishUseCase;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
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
    private final WebsiteThemeCommandPort websiteThemeCommandPort;
    private final RemoteCommandPort remoteCommandPort;

    @Override
    public void publish(Site site) {

        createDomain(site);
        enableSsl(site);
        createDb(site);
        downloadWebsite(site);
        createConfig(site);
        installWebsite(site);
        installTheme(site);

    }

    private void createDomain(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.DOMAIN_CREATION_FAILED) {
            FuncUtils.runOrThrow(() -> hostingPort.createDomain(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to select domain.", Status.DOMAIN_CREATION_FAILED));
            log.info("Selected domain for site: {}.", site.getId());
        }
    }

    private void enableSsl(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.SSL_ENABLE_FAILED) {
            FuncUtils.runOrThrow(() -> hostingPort.enableSsl(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to enable ssl for domain.", Status.SSL_ENABLE_FAILED));
            log.info("Enabled ssl for domain: {}.", site.getHostname());
        }
    }

    private void createDb(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.DB_CREATION_FAILED) {
            FuncUtils.runOrThrow(() -> hostingPort.createDb(site.getDbName(), site.getDbPass()), new ThemePublishingException(site.getId(), "Failed to create db.", Status.DB_CREATION_FAILED));
            log.info("Initialized db for site: {}.", site.getId());
        }
    }

    private void downloadWebsite(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.WEBSITE_DOWNLOAD_FAILED) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.downloadWebsite(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to download WordPress.", Status.WEBSITE_DOWNLOAD_FAILED));
            log.info("Downloaded WordPress for site: {}.", site.getId());
        }
    }

    private void createConfig(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.WEBSITE_CONFIGURATION_FAILED) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.createConfig(site.getDbName(), site.getDbPass(), site.getHostname()), new ThemePublishingException(site.getId(), "Failed to configure WordPress.", Status.WEBSITE_CONFIGURATION_FAILED));
            log.info("Configured WordPress for site: {}.", site.getId());
        }
    }

    private void installWebsite(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.WEBSITE_INSTALLATION_FAILED) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.installWebsite(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to install WordPress.", Status.WEBSITE_INSTALLATION_FAILED));
            log.info("Installed WordPress for site: {}.", site.getId());
        }
    }

    private void installTheme(Site site) {
        if (site.getStatus() == Status.CREATED || site.getStatus() == Status.THEME_INSTALLATION_FAILED) {
            FuncUtils.runOrThrow(() -> installTheme(site.getHostname(), site.getThemeId()), new ThemePublishingException(site.getId(), "Failed to install theme.", Status.THEME_INSTALLATION_FAILED));
            log.info("Installed theme for site: {}.", site.getId());
        }
    }

    private void installTheme(String hostname, String themeId) {
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
