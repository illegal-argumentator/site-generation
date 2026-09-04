package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class LuckyCasinoTemplateGenerationStrategy implements TemplateGenerationStrategy{

    private final TemplateGenerationService generationService;

    private static final List<String> ELEMENT_IDS = List.of(
            "title",
            "#site-header",
            "#hero",
            "#stats",
            "#features",
            "#games",
            "#jackpot",
            "#faq",
            "#cta",
            "#site-footer"
    );

    @Override
    public byte[] generate(ThemeGenerationRequest request) {
        return generationService.generate(ELEMENT_IDS, request);
    }

    @Override
    public TemplateType getType() {
        return TemplateType.LUCKY_CASINO;
    }
}
