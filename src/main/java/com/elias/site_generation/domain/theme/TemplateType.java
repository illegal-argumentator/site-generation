package com.elias.site_generation.domain.theme;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum TemplateType {

    LUCKY_CASINO("lucky-casino", List.of(TemplateComponent.HOME, TemplateComponent.COOKIES, TemplateComponent.FAQ));

    private final String name;
    private final List<TemplateComponent> components;
}
