package com.elias.site_generation.adapter.theme.out.persistence;

import com.elias.site_generation.adapter.file.out.FileService;
import com.elias.site_generation.adapter.theme.out.mapper.ThemeMapper;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.theme.ThemeCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PostgresThemeCommandAdapter implements ThemeCommandPort {

    private final PostgresThemeRepository repository;
    private final FileService fIleService;
    private final ThemeMapper mapper;

    @Override
    public void save(Theme theme) {
        PostgresTheme entity = repository.save(mapper.toEntity(theme));
        fIleService.write(entity.getId(), theme.data());
    }

}
