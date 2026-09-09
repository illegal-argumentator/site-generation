package com.elias.site_generation.adapter.theme.out.generation.component.template;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
import com.elias.site_generation.adapter.theme.out.prompt.ThemePromptPolicyBuilder;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
final class TemplateGenerator {

    private final AiService aiService;
    private final ExecutorService executor;

    public byte[] generateCss(String content) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(content, CasinoThemePromptPolicy.LUCKY_CASINO_STYLES_SAMPLE);
        String response = aiService.generate(new AiRequest(prompt, content));
        return response.getBytes();
    }

    public byte[] generateHtml(byte[] index, Set<String> elements, ThemeGenerationRequest request) {
        Document html = Jsoup.parse(new String(index));

        Map<String, String> generatedElements = generateElements(elements, html, request);
        generatedElements.forEach((elementId, element) -> replaceElement(html, elementId, element));

        return html.outerHtml().getBytes();
    }

    private Map<String, String> generateElements(Set<String> elements, Document html, ThemeGenerationRequest request) {
        Map<String, CompletableFuture<String>> futures = new LinkedHashMap<>();

        for (String elementId : elements) {
            Element element = html.selectFirst(elementId);
            if (element == null) continue;

            String elementHtml = element.outerHtml();
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> generateElement(elementHtml, request), executor);
            futures.put(elementId, future);
        }

        CompletableFuture.allOf(futures.values().toArray(CompletableFuture[]::new)).join();
        return futures.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().join()));
    }

    private String generateElement(String elementHtml, ThemeGenerationRequest request) {
        ThemePromptPolicyBuilder.Rules rules = new ThemePromptPolicyBuilder.Rules(request.title(), request.language(), elementHtml);
        String prompt = ThemePromptPolicyBuilder.buildHtmlChangePrompt(rules);
        return aiService.generate(new AiRequest(prompt, request.content()));
    }

    private void replaceElement(Document html, String elementId, String generatedHtml) {
        Element original = html.selectFirst(elementId);
        if (original == null) return;

        Element replacement = Jsoup
                .parseBodyFragment(generatedHtml)
                .body()
                .children()
                .first();

        if (replacement != null) original.replaceWith(replacement);
    }
}
