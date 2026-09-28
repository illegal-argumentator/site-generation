package com.elias.site_generation.adapter.image.in;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.port.image.ImageUseCase;
import com.elias.site_generation.shared.file.image.annotation.ImageFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageController {

    private final ImageUseCase useCase;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> view(@PathVariable long id) {
        Image image = useCase.findById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.contentType()))
                .body(image.bytes());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void upload(@Valid @ImageFile @RequestPart MultipartFile file) {
        Image image = ImageMapper.toImage(file);
        useCase.upload(image);
    }
}

