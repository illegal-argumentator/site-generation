package com.elias.site_generation.adapter.theme.out.prompt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CasinoThemePromptPolicy {

    public static final String CASINO_STYLES_TEMPLATE = """
            You are a senior UI designer and CSS architect.
            
            Your task is to redesign the appearance of an existing website by modifying ONLY
            the values of the existing CSS declarations.
            
            Rules
            
            - Preserve the CSS structure exactly.
            - Preserve every selector exactly.
            - Preserve selector order.
            - Preserve nested rules.
            - Preserve media queries.
            - Preserve pseudo classes and pseudo elements.
            - Preserve keyframes.
            - Preserve comments whenever possible.
            
            Do NOT:
            
            - add new selectors
            - remove selectors
            - rename selectors
            - merge selectors
            - split selectors
            - reorder selectors
            - remove CSS properties unless absolutely required
            - generate HTML
            - generate explanations
            
            You MAY modify only property values, including:
            
            - colors
            - gradients
            - typography
            - spacing
            - sizing
            - shadows
            - borders
            - border-radius
            - opacity
            - transitions
            - transforms
            - filters
            - backgrounds
            - hover styles
            - animations
            - responsive values
            
            Client request
            
            Below is a request from the client describing the website and/or the desired look.
            If it contains any preference relevant to visual style — theme, mood, color palette,
            specific colors, typography, brand feel, level of boldness/minimalism, or any other
            styling instruction — follow it as the primary direction for the redesign, and only
            randomize the aspects the client did NOT specify. If the client request contains no
            styling-relevant information at all (or is empty), ignore it and generate a
            completely new random visual direction as usual.
            
            Client request:
            
            %s
            
            Generate a completely new random visual direction for everything not covered by
            the client request above.
            
            Randomize (unless constrained by the client request):
            
            - color palette
            - typography
            - corner radius
            - shadows
            - gradients
            - glassmorphism level
            - neumorphism level
            - spacing scale
            - button appearance
            - cards
            - navigation
            - hero appearance
            - forms
            - footer
            - visual density
            
            The result must remain:
            
            - modern
            - coherent
            - responsive
            - visually balanced
            - professional
            
            Do not copy existing brands.
            
            Return ONLY CSS.
            
            The output must have the exact same structure as the input CSS.
            
            Only property values may change.
            
            Here are styles:
            
            %s
            
            The stylesheet must be ready to insert directly inside:
            
            <style>
                [GENERATED CSS]
            </style>
            
            in the `<head>` of `index.html`
            """;

    private static final String CASINO_STYLES_TEMPLATE_WITH_CREATED = """
            %s
            
            Below are the styles generated for another page of the same website. Use them as a reference to maintain a consistent visual style and design across all pages:
            %s
            """;

    public static String buildCasinoStylesWithCreated(String prompt, String createdCss) {
        return CASINO_STYLES_TEMPLATE_WITH_CREATED.formatted(prompt, createdCss);
    }
}
