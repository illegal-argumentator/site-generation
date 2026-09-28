package com.elias.site_generation.adapter.image.out.mapper;

import com.elias.site_generation.adapter.image.out.persistence.PostgresImage;
import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface ImageMapper {

    PostgresImage toEntity(Image image);

    Image toImage(PostgresImage entity);

}
