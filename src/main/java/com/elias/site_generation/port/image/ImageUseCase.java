package com.elias.site_generation.port.image;

import com.elias.site_generation.domain.image.Image;

public interface ImageUseCase {

    void upload(Image image);
    Image findById(long id);

}
