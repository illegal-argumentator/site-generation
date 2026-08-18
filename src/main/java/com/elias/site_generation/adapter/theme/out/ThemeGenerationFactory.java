package com.elias.site_generation.adapter.theme.out;

import com.elias.site_generation.adapter.theme.out.strategy.TemplateGenerationStrategy;
import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class ThemeGenerationFactory {

    private final List<TemplateGenerationStrategy> strategies;

    public TemplateGenerationStrategy getStrategy(TemplateType type) {
        return strategies.stream()
                .filter(strategy -> strategy.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Theme generation strategy not found by type: %s.".formatted(type.getName())));
    }

}
