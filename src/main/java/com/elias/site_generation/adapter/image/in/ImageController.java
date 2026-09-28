package com.elias.site_generation.adapter.image.in;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.port.image.ImageUseCase;
import com.elias.site_generation.shared.file.image.annotation.ImageFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageUseCase useCase;

    @PostMapping("/upload")
    public void upload(@Valid @ImageFile @RequestPart MultipartFile file) {
        Image image = ImageMapper.toImage(file);
        useCase.upload(image);
    }
}
