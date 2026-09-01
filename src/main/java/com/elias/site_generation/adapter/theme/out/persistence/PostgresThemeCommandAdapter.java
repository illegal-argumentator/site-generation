package com.elias.site_generation.adapter.theme.out.persistence;

import com.elias.site_generation.adapter.theme.out.mapper.ThemeMapper;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.theme.exception.ThemeNotFoundException;
import com.elias.site_generation.port.theme.ThemeCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresThemeCommandAdapter implements ThemeCommandPort {

    private final ThemeMapper mapper;
    private final PostgresThemeRepository repository;

    @Override
    public String save() {
        PostgresTheme entity = repository.save(PostgresTheme.from());
        return entity.getId();
    }

    @Override
    public Theme update(String id, String title) {
        PostgresTheme entity = repository.findById(id)
                .orElseThrow(() -> new ThemeNotFoundException("Theme not found."));

        entity.setTitle(title);
        PostgresTheme updated = repository.save(entity);
        return mapper.toTheme(updated);
    }
}
