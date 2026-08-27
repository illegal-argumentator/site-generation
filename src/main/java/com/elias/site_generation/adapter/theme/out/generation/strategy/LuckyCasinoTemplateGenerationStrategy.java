package com.elias.site_generation.adapter.theme.out.generation.strategy;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
import com.elias.site_generation.adapter.theme.out.prompt.ThemePromptPolicyBuilder;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
class LuckyCasinoTemplateGenerationStrategy implements TemplateGenerationStrategy {

    private static final String MAIN_PAGE_NAME = "index.html";
    private static final String STYLE_ELEMENT = "style";

    private static final List<String> ELEMENT_IDS = List.of(
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

    private final ZipFilePort zipFilePort;
    private final AiService aiService;
    private final ExecutorService executor;

    @Override
    public byte[] generate(ThemeGenerationRequest request) {
        byte[] index = zipFilePort.extract(MAIN_PAGE_NAME, request.template());

        Map<String, byte[]> files = Map.of(MAIN_PAGE_NAME, generateHtml(index,  generateStyle(request), request));
        return zipFilePort.update(request.template(), files);
    }

    private byte[] generateHtml(byte[] index, byte[] style ,ThemeGenerationRequest request) {
        Document html = parseHtml(index);
        Map<String, CompletableFuture<String>> generatedElements = generateElements(html, request);

        applyGeneratedElements(html, generatedElements);
        applyGeneratedStyles(html,  new String(style, StandardCharsets.UTF_8));

        return html.outerHtml().getBytes(StandardCharsets.UTF_8);
    }

    private byte[] generateStyle(ThemeGenerationRequest request) {
        return generateDesign(request).getBytes(StandardCharsets.UTF_8);
    }

    private Document parseHtml(byte[] content) {
        return Jsoup.parse(new String(content, StandardCharsets.UTF_8));
    }

    private String generateDesign(ThemeGenerationRequest request) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(request.content(), CasinoThemePromptPolicy.LUCKY_CASINO_STYLES_SAMPLE);
        AiRequest aiRequest = new AiRequest(prompt, request.content());
        return aiService.generate(aiRequest);
    }

    private Map<String, CompletableFuture<String>> generateElements(Document html, ThemeGenerationRequest request) {
        Map<String, CompletableFuture<String>> futures = new LinkedHashMap<>();

        for (String elementId : ELEMENT_IDS) {
            Element element = html.selectFirst(elementId);
            if (element == null) {
                continue;
            }

            String elementHtml = element.outerHtml();
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                            generateElement(elementHtml, request), executor
            );

            futures.put(elementId, future);
        }

        return futures;
    }

    private String generateElement(String elementHtml, ThemeGenerationRequest request) {
        ThemePromptPolicyBuilder.Rules rules = new ThemePromptPolicyBuilder.Rules(request.language(), elementHtml);
        String prompt = ThemePromptPolicyBuilder.buildHtmlChangePrompt(rules);
        return aiService.generate(new AiRequest(prompt, request.content()));
    }

    private void applyGeneratedStyles(Document html, String generatedCss) {
        Element style = html.head().selectFirst(STYLE_ELEMENT);

        if (style == null) {
            style = html.createElement(STYLE_ELEMENT);
            html.head().appendChild(style);
        }

        style.text(generatedCss);
    }

    private void applyGeneratedElements(Document html, Map<String, CompletableFuture<String>> generatedElements) {
        CompletableFuture.allOf(generatedElements.values().toArray(CompletableFuture[]::new)).join();

        generatedElements.forEach((elementId, future) ->
                replaceElement(html, elementId, future.join())
        );
    }

    private void replaceElement(Document html, String elementId, String generatedHtml) {
        Element original = html.selectFirst(elementId);

        if (original == null) {
            return;
        }

        Element replacement = Jsoup
                .parseBodyFragment(generatedHtml)
                .body()
                .children()
                .first();

        if (replacement != null) {
            original.replaceWith(replacement);
        }
    }

    @Override
    public TemplateType getType() {
        return TemplateType.LUCKY_CASINO;
    }
}