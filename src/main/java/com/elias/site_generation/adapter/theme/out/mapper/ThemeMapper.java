package com.elias.site_generation.adapter.theme.out.mapper;

import com.elias.site_generation.adapter.theme.out.persistence.PostgresTheme;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface ThemeMapper {

    Theme toTheme(PostgresTheme entity);

}
