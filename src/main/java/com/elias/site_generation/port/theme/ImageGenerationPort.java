package com.elias.site_generation.port.theme;

import java.util.Map;

public interface ImageGenerationPort {

    Map<String, byte[]> generate(byte[] html);

}
