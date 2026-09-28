package com.elias.site_generation.adapter.image.out.persistence;

import com.elias.site_generation.adapter.image.out.mapper.ImageMapper;
import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.domain.image.exception.ImageNotFoundException;
import com.elias.site_generation.port.image.ImageQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostgresImageQueryAdapter implements ImageQueryPort {

    private final ImageMapper mapper;
    private final PostgresImageRepository repository;

    @Override
    public Image findById(long id) {
        PostgresImage entity = findByIdOrThrow(id);
        return mapper.toImage(entity);
    }

    private PostgresImage findByIdOrThrow(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ImageNotFoundException("Image not found."));
    }
}
