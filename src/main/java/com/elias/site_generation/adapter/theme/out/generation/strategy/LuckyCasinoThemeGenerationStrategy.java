package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.generation.component.ThemeGenerationFacade;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoStyleSamples;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
import com.elias.site_generation.domain.theme.TemplateComponent;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.shared.props.TemplateProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RequiredArgsConstructor
class LuckyCasinoThemeGenerationStrategy implements ThemeGenerationStrategy {

    private final TemplateProps templateProps;
    private final ThemeGenerationFacade generationService;

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

    private final Set<String> FAQ_ELEMENTS = Set.of(
            "title",
            "#site-header",
            "#page-hero",
            "#faq-content",
            "#site-footer"
    );

    @Override
    public byte[] generate(ThemeGenerationRequest request) {
        return generationService.generate(TemplateType.LUCKY_CASINO, buildPages(request), request);
    }

    private Map<String, ElementPayload> buildPages(ThemeGenerationRequest request) {
        Map<String, ElementPayload> payloadMap = new HashMap<>();

        for (TemplateComponent component : getType().getComponents()) {
            switch (component) {
                case HOME -> payloadMap.put(templateProps.getIndexFile(), buildIndexPayload(request.content()));
                case FAQ -> payloadMap.put(templateProps.getCookiesFile(), buildCookiesPayload(request.content()));
                case COOKIES -> payloadMap.put(templateProps.getFaqFile(), buildFaqPayload(request.content()));
            }
        }

        return payloadMap;
    }

    private ElementPayload buildIndexPayload(String content) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(content, CasinoStyleSamples.LUCKY_CASINO_HOME_PAGE_STYLES);
        return ElementPayload.from(prompt, INDEX_ELEMENTS);
    }

    private ElementPayload buildCookiesPayload(String content) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(content, CasinoStyleSamples.LUCKY_CASINO_COOKIES_PAGE_STYLES);
        return ElementPayload.from(prompt, COOKIES_ELEMENTS);
    }

    private ElementPayload buildFaqPayload(String content) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(content, CasinoStyleSamples.LUCKY_CASINO_FAQ_STYLES);
        return ElementPayload.from(prompt, FAQ_ELEMENTS);
    }

    @Override
    public TemplateType getType() {
        return TemplateType.LUCKY_CASINO;
    }
}
