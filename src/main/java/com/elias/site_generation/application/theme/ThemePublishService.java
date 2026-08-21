package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.theme.ThemePublishUseCase;
import com.elias.site_generation.port.website.WebsiteThemePort;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class ThemePublishService implements ThemePublishUseCase {

    private final FilePathProps pathProps;
    private final HostingPort hostingPort;
    private final WebsiteThemePort websiteThemePort;

    @Override
    public void publish(long siteId, Theme theme) {
        hostingPort.createDomain();
        log.info("Selected domain for site: {}.", siteId);

        hostingPort.createDb();
        log.info("Initialized db for site: {}.", siteId);

        websiteThemePort.installWebsite();
        log.info("Installed WordPress for site: {}.", siteId);

        websiteThemePort.createConfig();
        log.info("Configured WordPress for site: {}.", siteId);

        websiteThemePort.installTheme(FileUtils.getFilePath(theme.id(), pathProps.getThemes()));
        log.info("Installed theme for site: {}.", siteId);
    }
}
