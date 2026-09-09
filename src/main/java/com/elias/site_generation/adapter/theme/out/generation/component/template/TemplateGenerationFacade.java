package com.elias.site_generation.adapter.theme.out.generation.component.template;

import com.elias.site_generation.adapter.theme.out.generation.zip.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
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

    private final TemplateProps props;
    private final ZipFilePort zipFilePort;

    private final TemplateGenerator templateGenerator;
    private final TemplateComponentsApplier componentsApplier;

    public byte[] generate(Set<String> elements, ThemeGenerationRequest request) {
        byte[] indexExample = zipFilePort.extract(props.getIndexFile(), request.template());
        log.info("Extracted index from template.");
        byte[] generatedCss = templateGenerator.generateCss(request.content());
        log.info("Generated css.");

        byte[] generatedHtml = templateGenerator.generateHtml(indexExample, elements, request);
        log.info("Generated html.");
        TemplateComponentsApplier.IndexComponent indexComponent = TemplateComponentsApplier.IndexComponent.from(generatedHtml, generatedCss);
        byte[] appliedIndex = componentsApplier.applyIndex(indexComponent, request.images().keySet());
        log.info("Applied files to index.");

        return updateZip(request.template(), Map.of(props.getIndexFile(), appliedIndex), request.images());
    }

    @SafeVarargs
    private byte[] updateZip(byte[] template, Map<String, byte[]>... entries) {
        Map<String, byte[]> all = Arrays.stream(entries)
                .flatMap(entry -> entry.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return zipFilePort.update(template, all);
    }


}