package com.elias.site_generation.domain.template;

import com.elias.site_generation.domain.image.Image;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.With;

public record Template(

        long id,

        String name,
        String description,

        @With
        Image image,

        TemplateType type

) {
}
