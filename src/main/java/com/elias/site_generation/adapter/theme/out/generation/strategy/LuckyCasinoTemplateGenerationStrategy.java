package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.generation.component.template.TemplateGenerationFacade;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
class LuckyCasinoTemplateGenerationStrategy implements TemplateGenerationStrategy{

    private final TemplateGenerationFacade generationService;

    private static final Set<String> ELEMENT_IDS = Set.of(
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
        return generationService.generate(TemplateType.LUCKY_CASINO, ELEMENT_IDS, request);
    }

    @Override
    public TemplateType getType() {
        return TemplateType.LUCKY_CASINO;
    }
}
