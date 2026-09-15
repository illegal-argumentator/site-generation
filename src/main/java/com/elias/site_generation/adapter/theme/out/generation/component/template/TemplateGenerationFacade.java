package com.elias.site_generation.adapter.theme.out.generation.component.template;

import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.shared.props.TemplateProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public final class TemplateGenerationFacade {

    private final TemplateProps templateProps;
    private final ZipFilePort zipFilePort;

    private final TemplateGenerator templateGenerator;
    private final TemplateComponentsApplier componentsApplier;

    public byte[] generate(TemplateType type, Map<String, Set<String>> elements, ThemeGenerationRequest request) {
        Map<String, byte[]> pages = new HashMap<>();

        for (Map.Entry<String, Set<String>> entry : elements.entrySet()) {
            PageComponent generatePage = generatePage(entry.getKey(), entry.getValue(), request);
            byte[] appliedIndex = componentsApplier.applyIndex(type, generatePage, request.images().keySet());
            pages.put(entry.getKey(), appliedIndex);
        }


        return updateZip(request.template(), pages, mapImagesAbsolutPath(type, request.images()));
    }

    public PageComponent generatePage(String filename, Set<String> elements, ThemeGenerationRequest request) {
        byte[] zipComponent = zipFilePort.extract(filename, request.template());
        byte[] css = templateGenerator.generateCss(request.content());
        byte[] html = templateGenerator.generateHtml(zipComponent, elements, request);

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
                        Map.Entry::getValue)
                );
    }

}