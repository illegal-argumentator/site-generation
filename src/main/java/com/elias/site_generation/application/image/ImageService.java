package com.elias.site_generation.application.image;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.port.image.ImageCommandPort;
import com.elias.site_generation.port.image.ImageUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageUseCase {

    private final ImageCommandPort commandPort;

    @Override
    public void upload(Image image) {
        commandPort.upload(image);
    }

}
