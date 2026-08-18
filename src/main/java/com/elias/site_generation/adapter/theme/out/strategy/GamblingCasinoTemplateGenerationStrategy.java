package com.elias.site_generation.adapter.theme.out.strategy;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.file.out.ZipFilePort;
import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
class GamblingCasinoTemplateGenerationStrategy implements TemplateGenerationStrategy {

    private final ZipFilePort zipFilePort;
    private final AiService aiService;

    @Override
    public byte[] generate(ThemeGenerationRequest request) {
        return processZip(request);
    }

    public byte[] processZip(ThemeGenerationRequest request) {
        Map<String, byte[]> files = zipFilePort.extract(request.template()), generatedFiles = new HashMap<>();

        for (Map.Entry<String, byte[]> entry : files.entrySet()) {
            byte[] generatedFile = generate(entry.getKey(), entry.getValue(), request);
            generatedFiles.put(entry.getKey(), generatedFile);
        }

        return zipFilePort.update(request.template(), generatedFiles);
    }

    private byte[] generate(String name, byte[] content, ThemeGenerationRequest request) {
        return content;
    }

    @Override
    public TemplateType getType() {
        return TemplateType.GAMBLING_CASINO;
    }
}
