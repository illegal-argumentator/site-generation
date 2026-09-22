package com.elias.site_generation.domain.theme;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateComponent {

    HOME("index.html"),
    FAQ("faq.html"),
    COOKIES("cookies.html");

    private final String name;

}
