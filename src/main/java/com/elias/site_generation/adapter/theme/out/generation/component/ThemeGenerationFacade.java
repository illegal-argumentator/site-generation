package com.elias.site_generation.adapter.theme.out.generation.component;

import com.elias.site_generation.adapter.theme.out.generation.strategy.ElementPayload;
import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoThemePromptPolicy;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.shared.props.TemplateProps;
import com.elias.site_generation.shared.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public final class ThemeGenerationFacade {

    private final TemplateProps templateProps;
    private final ZipFilePort zipFilePort;

    private final ThemeGenerator themeGenerator;
    private final ThemeComponentsApplier componentsApplier;

    public byte[] generate(TemplateType type, Map<String, ElementPayload> elements, ThemeGenerationRequest request) {
        String firstGeneratedCss = "";
        Map<String, byte[]> pages = new HashMap<>();

        for (Map.Entry<String, ElementPayload> entry : elements.entrySet()) {
            ElementPayload value = entry.getValue(); String key = entry.getKey();

            if (!StringUtils.isEmpty(firstGeneratedCss)) {
                value = value.withPrompt(CasinoThemePromptPolicy.buildCasinoStylesWithCreated(value.prompt(), firstGeneratedCss));
            }

            PageComponent generatePage = generatePage(key, value, request);
            if (StringUtils.isEmpty(firstGeneratedCss)) firstGeneratedCss = new String(generatePage.css());

            byte[] appliedIndex = componentsApplier.applyIndex(type, generatePage, request.images().keySet());
            pages.put(key, appliedIndex);

            log.info("Generated file: {}.", key);
        }

        return updateZip(request.template(), pages, mapImagesAbsolutPath(type, request.images()));
    }

    public PageComponent generatePage(String filename, ElementPayload payload, ThemeGenerationRequest request) {
        byte[] zipComponent = zipFilePort.extract(filename, request.template());
        byte[] css = themeGenerator.generateCss(request.content(), payload.prompt());
        byte[] html = themeGenerator.generateHtml(zipComponent, payload.tags(), request);

        return PageComponent.from(html, css);
    }

    @SafeVarargs
    private byte[] updateZip(byte[] template, Map<String, byte[]>... entries) {
        Map<String, byte[]> all = Arrays.stream(entries)
                .flatMap(entry -> entry.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return zipFilePort.update(template, all);
    }

    private Map<String, byte[]> mapImagesAbsolutPath(TemplateType type, Map<String, byte[]> images) {
        return images.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> templateProps.getAssetsOriginPathTemplate().formatted(type.getName())  + entry.getKey(),
                        Map.Entry::getValue
                ));
    }
}