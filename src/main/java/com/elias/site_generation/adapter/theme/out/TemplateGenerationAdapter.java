package com.elias.site_generation.adapter.theme.out;

import com.elias.site_generation.adapter.theme.in.dto.ThemeGenerationRequest;
import com.elias.site_generation.adapter.theme.out.strategy.TemplateGenerationStrategy;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class TemplateGenerationAdapter implements TemplateGenerationPort {

    private final ThemeGenerationFactory generationFactory;

    @Override
    public byte[] generate(TemplateType type, ThemeGenerationRequest request) {
        TemplateGenerationStrategy strategy = generationFactory.getStrategy(type);
        return strategy.generate(request);
    }

}
