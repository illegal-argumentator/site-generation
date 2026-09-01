package com.elias.site_generation.adapter.theme.out.persistence;

import com.elias.site_generation.adapter.theme.out.mapper.ThemeMapper;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.theme.ThemeCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresThemeCommandAdapter implements ThemeCommandPort {

    private final ThemeMapper mapper;
    private final PostgresThemeRepository repository;

    @Override
    public void save(Theme theme) {
        PostgresTheme entity = mapper.toEntity(theme);
        repository.save(entity);
    }

}
