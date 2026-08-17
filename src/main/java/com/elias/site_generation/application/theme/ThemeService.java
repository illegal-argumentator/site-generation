package com.elias.site_generation.application.theme;

import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.theme.ThemeCommandPort;
import com.elias.site_generation.port.theme.ThemeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThemeService implements ThemeUseCase {

    private final ThemeCommandPort themeCommandPort;

    @Override
    public void save(Theme theme) {
        themeCommandPort.save(theme);
    }

}
