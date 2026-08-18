package com.elias.site_generation.adapter.theme.out.prompt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ThemePromptPolicyBuilder {

    private static final String MAIN_RULE = """
            You are an expert WordPress front-end developer.
            
            You modify existing website files.
            
            The provided file is part of an existing WordPress theme.
            
            Your primary objective is to preserve the original structure while changing only the visual appearance and textual content.
            
            Rules:
            
            1. Never change the HTML structure unless explicitly requested.
            2. Never remove or rename classes.
            3. Never remove or rename IDs.
            4. Never change JavaScript bindings.
            5. Never remove forms.
            6. Never remove WordPress placeholders.
            7. Never change file paths.
            8. Never modify functionality.
            9. Preserve responsive behavior.
            10. Preserve accessibility.
            11. Keep valid HTML and CSS.
            12. Do not add explanations.
            13. Return only the complete updated file.
            14. If a section is not mentioned in the request, leave it unchanged.
            15. If the request conflicts with preserving functionality, preserve functionality.
            """;

    private static final String REQUEST_TEMPLATE = """
            Client request:
            
            %s
            
            Target language:
            
            %s
            
            Current file:
            
            %s
            
            File content:
            
            %s
            """;

    private static final String CSS_CHANGE_TEMPLATE = """
            Modify ONLY the CSS.
            
            Rules:
            
            - Keep all selectors.
            - Do not rename classes.
            - Do not remove responsive rules.
            - Keep layout intact.
            - Only change colors, typography, spacing, shadows, borders, animations when needed.
            - Return only CSS.
            """;

    private static final String HTML_CHANGE_TEMPLATE = """
            Modify ONLY the content and styling-related markup.
            
            Rules:
            
            - Preserve the existing DOM hierarchy.
            - Preserve classes.
            - Preserve IDs.
            - Preserve data-* attributes.
            - Preserve JavaScript hooks.
            - Translate all visible text into %s.
            - Adapt the content according to the client request.
            - Do not explain.
            - Return only HTML.
            """;

    public static String buildCssChangePrompt(Rules rules) {
        return buildMainRule(rules).concat(CSS_CHANGE_TEMPLATE);
    }

    public static String buildHtmlChangePrompt(Rules rules) {
        return buildMainRule(rules).concat(HTML_CHANGE_TEMPLATE.formatted(rules.language));
    }

    private static String buildMainRule(Rules rules) {
        return MAIN_RULE
                .concat("\n")
                .concat(REQUEST_TEMPLATE.formatted(rules.request, rules.language, rules.filename, rules.content))
                .concat("\n");
    }

    public record Rules(
            String request,
            String language,
            String filename,
            String content
    ) {

    }
}
