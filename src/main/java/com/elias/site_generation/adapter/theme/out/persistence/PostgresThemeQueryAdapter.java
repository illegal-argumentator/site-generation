package com.elias.site_generation.adapter.theme.out.persistence;

import com.elias.site_generation.adapter.theme.out.mapper.ThemeMapper;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.exception.ThemeNotFoundException;
import com.elias.site_generation.port.theme.ThemeQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PostgresThemeQueryAdapter implements ThemeQueryPort {

    private final ThemeMapper mapper;
    private final PostgresThemeRepository repository;

    @Override
    public Theme getById(String id) {
        return mapper.toTheme(getOrThrow(id));
    }

    private PostgresTheme getOrThrow(String id) {
        return repository.findById(id).orElseThrow(() -> new ThemeNotFoundException("Theme not found."));
    }
}
