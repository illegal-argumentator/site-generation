package com.elias.site_generation.adapter.ai.out.gpt;

import com.elias.site_generation.adapter.ai.out.AiImageService;
import com.elias.site_generation.adapter.ai.out.exception.AiException;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
class GptAiImageService implements AiImageService {

    private final ImageModel imageModel;

    @Override
    public byte[] generate(String prompt) {
        try {
            ImageGeneration result = imageModel.call(new ImagePrompt(prompt)).getResult();

            if (result == null) {
                throw new IllegalStateException("Result is not present after image generation.");
            }

            String b64 = Objects.requireNonNull(result.getOutput().getB64Json());
            return Base64.getDecoder().decode(b64);
        } catch (Exception e) {
            throw new AiException("Unable to generate image: %s.".formatted(e.getMessage()));
        }
    }
}