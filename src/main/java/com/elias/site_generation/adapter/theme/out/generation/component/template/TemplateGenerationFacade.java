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

    public byte[] generate(TemplateType type, Set<String> elements, ThemeGenerationRequest request) {
        byte[] indexExample = zipFilePort.extract(templateProps.getIndexFile(), request.template());
        byte[] generatedCss = templateGenerator.generateCss(request.content());

        byte[] generatedHtml = templateGenerator.generateHtml(indexExample, elements, request);
        TemplateComponentsApplier.IndexComponent indexComponent = TemplateComponentsApplier.IndexComponent.from(generatedHtml, generatedCss);
        byte[] appliedIndex = componentsApplier.applyIndex(type, indexComponent, request.images().keySet());

        return updateZip(request.template(), Map.of(templateProps.getIndexFile(), appliedIndex), mapImagesAbsolutPath(type, request.images()));
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