package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.site.DbGenerationPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.theme.usecase.ThemeDeployUseCase;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import com.elias.site_generation.shared.utils.FuncUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ThemeDeployService implements ThemeDeployUseCase {

    private final DbGenerationPort dbGenerationPort;
    private final ThemeDeployActions deployActions;
    private final HostingPort hostingPort;
    private final WebsiteThemeCommandPort websiteThemeCommandPort;
    private final SiteCommandPort siteCommandPort;

    @Override
    public void deploy(Site site) {
        process(site);
        updatePublished(site);
    }

    private void process(Site site) {
        switch (site.getDeployStatus()) {
            case PENDING, IN_PROGRESS, DOMAIN_CREATION_FAILED:
                createDomain(site);
            case SSL_ENABLE_FAILED:
                enableSsl(site);
            case DB_CREATION_FAILED:
                createDb(site);
            case WEBSITE_DOWNLOAD_FAILED:
                downloadWebsite(site);
            case WEBSITE_CONFIGURATION_FAILED:
                createConfig(site);
            case WEBSITE_INSTALLATION_FAILED:
                installWebsite(site);
            case THEME_INSTALLATION_FAILED:
                installTheme(site);
        }
    }

    private void createDomain(Site site) {
        FuncUtils.runOrThrow(() -> hostingPort.createDomain(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to select domain.", DeployStatus.DOMAIN_CREATION_FAILED));
        log.info("Selected domain for site: {}.", site.getId());
    }

    private void enableSsl(Site site) {
        FuncUtils.runOrThrow(() -> hostingPort.enableSsl(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to enable ssl for domain. Possibly domain is overused.", DeployStatus.SSL_ENABLE_FAILED));
        log.info("Enabled ssl for domain: {}.", site.getHostname());
    }

    private void createDb(Site site) {
        Db db = dbGenerationPort.generate();
        FuncUtils.runOrThrow(() -> hostingPort.createDb(db), new ThemePublishingException(site.getId(), "Failed to create db.", DeployStatus.DB_CREATION_FAILED));

        Site update = Site.builder().db(db).build();
        siteCommandPort.update(site.getId(), update);
        log.info("Initialized db for site: {}.", site.getId());
    }

    private void downloadWebsite(Site site) {
        FuncUtils.runOrThrow(() -> websiteThemeCommandPort.downloadWebsite(site.getHostname()), new ThemePublishingException(site.getId(), "Failed to download WordPress.", DeployStatus.WEBSITE_DOWNLOAD_FAILED));
        log.info("Downloaded WordPress for site: {}.", site.getId());
    }

    private void createConfig(Site site) {
        FuncUtils.runOrThrow(() -> websiteThemeCommandPort.createConfig(site.getDb(), site.getHostname()), new ThemePublishingException(site.getId(), "Failed to configure WordPress.", DeployStatus.WEBSITE_CONFIGURATION_FAILED));
        log.info("Configured WordPress for site: {}.", site.getId());
    }

    private void installWebsite(Site site) {
        FuncUtils.runOrThrow(() -> websiteThemeCommandPort.installWebsite(site.getTheme().title(), site.getHostname()), new ThemePublishingException(site.getId(), "Failed to install WordPress.", DeployStatus.WEBSITE_INSTALLATION_FAILED));
        log.info("Installed WordPress for site: {}.", site.getId());
    }

    private void installTheme(Site site) {
        FuncUtils.runOrThrow(() -> deployActions.installTheme(site.getHostname(), site.getTheme().id()), (e) -> new ThemePublishingException(site.getId(), "Failed to install theme.", DeployStatus.THEME_INSTALLATION_FAILED, e));
        log.info("Installed theme for site: {}.", site.getId());
    }

    private void updatePublished(Site site) {
        Site update = Site.builder().failReason("").deployStatus(DeployStatus.PUBLISHED).build();
        siteCommandPort.update(site.getId(), update);
    }
}
