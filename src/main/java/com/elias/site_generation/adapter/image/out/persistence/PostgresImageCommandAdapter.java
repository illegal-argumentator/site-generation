package com.elias.site_generation.adapter.image.out.persistence;

import com.elias.site_generation.adapter.image.out.mapper.ImageMapper;
import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.port.image.ImageCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresImageCommandAdapter implements ImageCommandPort {

    private final ImageMapper mapper;
    private final PostgresImageRepository repository;

    @Override
    public void upload(Image image) {
        PostgresImage entity = mapper.toEntity(image);
        repository.save(entity);
    }
}
