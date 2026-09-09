package com.elias.site_generation.adapter.theme.out.generation.component.template;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.theme.out.generation.component.image.ImageGenerationPort;
import com.elias.site_generation.adapter.theme.out.generation.component.title.TitleGenerationPort;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
import com.elias.site_generation.adapter.theme.out.prompt.ThemePromptPolicyBuilder;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.shared.file.FilePath;
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

    public byte[] generate(TemplateType type, List<String> elements, ThemeGenerationRequest request) {
        byte[] index = zipFilePort.extract(props.getIndexFile(), request.template());

        Map<String, byte[]> images = imageGenerationPort.generate(index);
        byte[] html = generateHtml(index, images.keySet(), elements, request);

        return updateZip(request, Map.of(FilePath.from(props.getIndexFile()), html), mapToImagePaths(type, images));
    }

    @SafeVarargs
    private byte[] updateZip(ThemeGenerationRequest request, Map<FilePath, byte[]>... entries) {
        Map<FilePath, byte[]> all = Arrays.stream(entries)
                .flatMap(entry -> entry.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return zipFilePort.update(request.template(), all);
    }

    private byte[] generateHtml(byte[] index, Set<String> images, List<String> elements, ThemeGenerationRequest request) {
        Document html = Jsoup.parse(new String(index));

        String title = titleGenerationPort.generate();
        byte[] style = generateStyle(request);

        Map<String, String> generatedElements = generateElements(title, elements, html, request);
        return applyElements(html, style, images, generatedElements);
    }

    private byte[] applyElements(Document html, byte[] style, Set<String> images, Map<String, String> generatedElements) {
        applyGeneratedElements(html, generatedElements);
        applyImagePaths(html, images);
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

    private Map<String, String> generateElements(String title, List<String> elements, Document html, ThemeGenerationRequest request) {
        Map<String, CompletableFuture<String>> futures = new LinkedHashMap<>();

        for (String elementId : elements) {
            Element element = html.selectFirst(elementId);
            if (element == null) continue;

            String elementHtml = element.outerHtml();
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> generateElement(title, elementHtml, request), executor);
            futures.put(elementId, future);
        }

        CompletableFuture.allOf(futures.values().toArray(CompletableFuture[]::new)).join();
        return futures.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().join()));
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

    private void applyGeneratedElements(Document html, Map<String, String> generatedElements) {
        generatedElements.forEach((elementId, element) -> replaceElement(html, elementId, element));
    }

    private void applyImagePaths(Document html, Set<String> images) {
        Elements imageEls = html.select(props.getImagesClass());
        if (imageEls.size() < images.size()) throw new IllegalStateException("Not enough images for the page.");

        Iterator<String> iterator = images.iterator();
        for (Element imageEl : imageEls) {
            imageEl.attr(SOURCE_ELEMENT, props.getAssetsLocalPath().concat(iterator.next()));
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

    private Map<FilePath, byte[]> mapToImagePaths(TemplateType type, Map<String, byte[]> files) {
        String basePath = type.getName().concat(props.getAssetsSourcePath());
        return files.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> FilePath.from(entry.getKey(), basePath.concat(entry.getKey())),
                        Map.Entry::getValue)
                );
    }

}