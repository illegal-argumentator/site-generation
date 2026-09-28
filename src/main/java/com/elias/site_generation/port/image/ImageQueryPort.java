package com.elias.site_generation.port.image;

import com.elias.site_generation.domain.image.Image;

public interface ImageQueryPort {

    Image findById(long id);

}
