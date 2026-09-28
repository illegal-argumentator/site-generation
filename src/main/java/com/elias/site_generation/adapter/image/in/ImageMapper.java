package com.elias.site_generation.adapter.image.in;

import com.elias.site_generation.adapter.image.in.exception.InvalidImageException;
import com.elias.site_generation.domain.image.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

final class ImageMapper {

    private ImageMapper() {
    }

    static Image toImage(MultipartFile file) {
        try {
            return Image.builder()
                    .contentType(file.getContentType())
                    .filename(file.getOriginalFilename())
                    .bytes(file.getBytes())
                    .build();
        } catch (IOException e) {
            throw new InvalidImageException("%s is invalid.".formatted(file.getOriginalFilename()));
        }
    }
}
