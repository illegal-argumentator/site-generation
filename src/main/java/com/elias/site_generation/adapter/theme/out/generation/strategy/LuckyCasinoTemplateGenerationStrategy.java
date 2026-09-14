package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.generation.component.template.TemplateGenerationFacade;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.shared.props.TemplateProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
class LuckyCasinoTemplateGenerationStrategy implements TemplateGenerationStrategy {

    private final TemplateProps templateProps;
    private final TemplateGenerationFacade generationService;

    private static final Set<String> INDEX_ELEMENTS = Set.of(
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

    private final Set<String> COOKIE_ELEMENTS = Set.of(
            "title",
            "#site-header",
            "#page-hero",
            "#policy-content",
            "#site-footer"
    );

    @Override
    public byte[] generate(ThemeGenerationRequest request) {
        Map<String, Set<String>> pages = Map.of(templateProps.getIndexFile(), INDEX_ELEMENTS, templateProps.getCookiesFile(), COOKIE_ELEMENTS);
        return generationService.generate(TemplateType.LUCKY_CASINO, pages, request);
    }

    @Override
    public TemplateType getType() {
        return TemplateType.LUCKY_CASINO;
    }
}
