package com.elias.site_generation.adapter.theme.out.generation.component.image;


import com.elias.site_generation.domain.theme.TemplateType;

import java.util.Map;

public interface ImageGenerationPort {

    Map<String, byte[]> generate(TemplateType type, byte[] html);

}
