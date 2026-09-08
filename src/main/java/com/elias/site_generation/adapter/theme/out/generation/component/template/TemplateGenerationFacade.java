package com.elias.site_generation.adapter.theme.out.generation.component.template;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.theme.out.generation.component.image.ImageGenerationPort;
import com.elias.site_generation.adapter.theme.out.generation.component.title.TitleGenerationPort;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
import com.elias.site_generation.adapter.theme.out.prompt.ThemePromptPolicyBuilder;
import com.elias.site_generation.shared.props.TemplateProps;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public final class TemplateGenerationFacade {

    private static final String STYLE_ELEMENT = "style", SOURCE_ELEMENT = "src";

    private final ImageGenerationPort imageGenerationPort;
    private final TitleGenerationPort titleGenerationPort;

    private final TemplateProps props;
    private final AiService aiService;
    private final ZipFilePort zipFilePort;
    private final ExecutorService executor;

    public byte[] generate(List<String> elements, ThemeGenerationRequest request) {
        byte[] index = zipFilePort.extract(props.getIndexFile(), request.template());

        String title = titleGenerationPort.generate();
        byte[] html = generateHtml(title, index, elements, request);
        Map<String, byte[]> images = imageGenerationPort.generate(html);

        applyImagePaths(Jsoup.parse(new String(html)), images.keySet());

        return updateZip(request, images, Map.of(props.getIndexFile(), html));
    }

    @SafeVarargs
    private byte[] updateZip(ThemeGenerationRequest request, Map<String, byte[]>... entries) {
        Map<String, byte[]> all = Arrays.stream(entries)
                .flatMap(entry -> entry.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return zipFilePort.update(request.template(), all);
    }

    private byte[] generateHtml(String title, byte[] index, List<String> elements, ThemeGenerationRequest request) {
        Document html = Jsoup.parse(new String(index));

        byte[] style = generateStyle(request);
        Map<String, CompletableFuture<String>> generatedElements = generateElements(title, elements, html, request);

        applyGeneratedElements(html, generatedElements);
        applyGeneratedStyles(html, new String(style));

        return html.outerHtml().getBytes();
    }

    private byte[] generateStyle(ThemeGenerationRequest request) {
        return generateDesign(request).getBytes();
    }

    private String generateDesign(ThemeGenerationRequest request) {
        String prompt = CasinoThemePromptPolicy.CASINO_STYLES_TEMPLATE.formatted(request.content(), CasinoThemePromptPolicy.LUCKY_CASINO_STYLES_SAMPLE);
        AiRequest aiRequest = new AiRequest(prompt, request.content());
        return aiService.generate(aiRequest);
    }

    private Map<String, CompletableFuture<String>> generateElements(String title, List<String> elements, Document html, ThemeGenerationRequest request) {
        Map<String, CompletableFuture<String>> futures = new LinkedHashMap<>();

        for (String elementId : elements) {
            Element element = html.selectFirst(elementId);
            if (element == null) continue;

            String elementHtml = element.outerHtml();
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> generateElement(title, elementHtml, request), executor);
            futures.put(elementId, future);
        }

        return futures;
    }

    private String generateElement(String title, String elementHtml, ThemeGenerationRequest request) {

        ThemePromptPolicyBuilder.Rules rules = new ThemePromptPolicyBuilder.Rules(title, request.language(), elementHtml);
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
        generatedElements.forEach((elementId, future) -> replaceElement(html, elementId, future.join()));
    }

    private void applyImagePaths(Document html, Set<String> paths) {
        Elements imageEls = html.select(props.getImagesClass());
        if (imageEls.size() < paths.size()) throw new IllegalStateException("Not enough images for the page.");

        Iterator<String> iterator = paths.iterator();
        for (Element imageEl : imageEls) {
            imageEl.attr(SOURCE_ELEMENT, iterator.next());
        }
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