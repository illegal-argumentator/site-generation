package com.elias.site_generation.adapter.theme.out.mapper;

import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Mapper(config = MapStructConfig.class)
public interface MultipartThemeMapper {

    default Theme toTheme(MultipartFile file) {
        try {
           return Theme.from(file.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid request file.");
        }
    }

}
