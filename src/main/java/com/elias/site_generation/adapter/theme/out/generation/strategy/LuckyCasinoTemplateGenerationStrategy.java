package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.generation.component.template.TemplateGenerationFacade;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
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

    private final Set<String> COOKIES_ELEMENTS = Set.of(
            "title",
            "#site-header",
            "#page-hero",
            "#policy-content",
            "#site-footer"
    );

    @Override
    public byte[] generate(ThemeGenerationRequest request) {
        Map<String, ElementPayload> pages = Map.of(
                templateProps.getIndexFile(), buildIndexPayload(request.content()),
                templateProps.getCookiesFile(), buildCookiesPayload(request.content())
        );
        return generationService.generate(TemplateType.LUCKY_CASINO, pages, request);
    }

    private ElementPayload buildIndexPayload(String content) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(content, CasinoThemePromptPolicy.LUCKY_CASINO_HOME_PAGE_STYLES_SAMPLE);
        return ElementPayload.from(prompt, INDEX_ELEMENTS);
    }

    private ElementPayload buildCookiesPayload(String content) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(content, CasinoThemePromptPolicy.LUCKY_CASINO_COOKIES_PAGE_STYLES_SAMPLE);
        return ElementPayload.from(prompt, COOKIES_ELEMENTS);
    }

    @Override
    public TemplateType getType() {
        return TemplateType.LUCKY_CASINO;
    }
}
