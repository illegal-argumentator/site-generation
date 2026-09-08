package com.elias.site_generation.adapter.theme.out.generation.component.image;


import java.util.Map;

public interface ImageGenerationPort {

    Map<String, byte[]> generate(byte[] html);

}
