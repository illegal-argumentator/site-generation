package com.elias.site_generation.adapter.image.in;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.port.image.ImageUseCase;
import com.elias.site_generation.shared.file.image.annotation.ImageFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageUseCase useCase;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@Valid @ImageFile @RequestParam MultipartFile file) {
        Image image = ImageMapper.toImage(file);
        useCase.upload(image);
    }
}
