package com.elias.site_generation.adapter.theme.out.generation.component.template;

public record PageComponent(byte[] index, byte[] css) {
    public static PageComponent from(byte[] index, byte[] css) {
        return new PageComponent(index, css);
    }
}