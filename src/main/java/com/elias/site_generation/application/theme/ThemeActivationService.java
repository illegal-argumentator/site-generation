package com.elias.site_generation.application.theme;

import com.elias.site_generation.port.theme.ThemeActivationUseCase;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeActivationService implements ThemeActivationUseCase {

    private final WebsiteThemeCommandPort websiteThemeCommandPort;

    @Override
    public void activate(String themeName, String hostname) {
        websiteThemeCommandPort.deleteIndex(hostname);
        websiteThemeCommandPort.activateTheme(themeName, hostname);
    }
}
