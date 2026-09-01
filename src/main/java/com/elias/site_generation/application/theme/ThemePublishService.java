package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.site.SiteCommandPort;
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
    private final SiteCommandPort siteCommandPort;

    @Override
    public void publish(Site site) {
        createDomain(site);
        enableSsl(site);
        createDb(site);
        downloadWebsite(site);
        createConfig(site);
        installWebsite(site);
        installTheme(site);

        siteCommandPort.update(site.getId(), Site.builder().deployStatus(DeployStatus.PUBLISHED).build());
    }

    private void createDomain(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.DOMAIN_CREATION_FAILED)) {
            FuncUtils.runOrThrow(() -> hostingPort.createDomain(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to select domain.", DeployStatus.DOMAIN_CREATION_FAILED));
            log.info("Selected domain for site: {}.", site.getId());
        }
    }

    private void enableSsl(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.SSL_ENABLE_FAILED)) {
            FuncUtils.runOrThrow(() -> hostingPort.enableSsl(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to enable ssl for domain.", DeployStatus.SSL_ENABLE_FAILED));
            log.info("Enabled ssl for domain: {}.", site.getHostname());
        }
    }

    private void createDb(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.DB_CREATION_FAILED)) {
            FuncUtils.runOrThrow(() -> hostingPort.createDb(site.getDb()), new ThemePublishingException(site.getId(), "Failed to create db.", DeployStatus.DB_CREATION_FAILED));
            log.info("Initialized db for site: {}.", site.getId());
        }
    }

    private void downloadWebsite(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.WEBSITE_DOWNLOAD_FAILED)) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.downloadWebsite(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to download WordPress.", DeployStatus.WEBSITE_DOWNLOAD_FAILED));
            log.info("Downloaded WordPress for site: {}.", site.getId());
        }
    }

    private void createConfig(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.WEBSITE_CONFIGURATION_FAILED)) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.createConfig(site.getDb(), site.getHostname()), new ThemePublishingException(site.getId(), "Failed to configure WordPress.", DeployStatus.WEBSITE_CONFIGURATION_FAILED));
            log.info("Configured WordPress for site: {}.", site.getId());
        }
    }

    private void installWebsite(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.WEBSITE_INSTALLATION_FAILED)) {
            FuncUtils.runOrThrow(() -> websiteThemeCommandPort.installWebsite(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to install WordPress.", DeployStatus.WEBSITE_INSTALLATION_FAILED));
            log.info("Installed WordPress for site: {}.", site.getId());
        }
    }

    private void installTheme(Site site) {
        if (shouldRunStep(site.getDeployStatus(), DeployStatus.THEME_INSTALLATION_FAILED)) {
            FuncUtils.runOrThrow(() -> installTheme(site.getHostname(), site.getTheme().id()), new ThemePublishingException(site.getId(), "Failed to install theme.", DeployStatus.THEME_INSTALLATION_FAILED));
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

    private boolean shouldRunStep(DeployStatus status, DeployStatus fail) {
        return (status == null || status == DeployStatus.PENDING) || status == fail;
    }

}
