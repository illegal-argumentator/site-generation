package com.elias.site_generation.adapter.theme.out.generation;

import java.util.Map;

public interface ThemeImageGenerationPort {

    Map<String, byte[]> generate(String prompt, byte[] source);

}
