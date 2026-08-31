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
            Target language:
            
            %s
            
            File content:
            
            %s
            """;

    private static final String HTML_CHANGE_TEMPLATE = """
            Modify ONLY the visible text content of the existing HTML. Do not touch
              anything else.
        
              Rules:
        
              - The provided HTML file already contains its complete structure and
                the complete styling.
              - Treat the HTML structure, all tags, attributes, classes, and IDs as
                the source of truth. Your only job is to replace text content.
        
              DO NOT:
        
              - add, remove, reorder, or restructure any HTML elements
              - change any tag names
              - add, remove, or modify any classes
              - add, remove, or modify any IDs
              - add, remove, or modify any data-* attributes
              - add a new <style> element
              - modify, remove, rewrite, or duplicate the existing <style> element
              - generate or change any CSS
              - use inline styles
              - change href, src, or other functional attribute values
              - change the number of elements (e.g. do not add or remove game cards,
                FAQ items, feature cards, nav links, footer columns, etc.) — every
                element keeps its place, only its text changes
        
              You MAY change:
        
              - the text inside headings, paragraphs, spans, buttons, links, labels
              - placeholder text in inputs
              - alt text on images
              - aria-label values, if present, so they stay accurate to the new text
              - numeric/stat values shown as text (e.g. jackpot amounts, RTP
                percentages, stats, countdown labels), as long as they stay
                plausible for a casino/slots website and consistent with the
                element they're in
        
              Brand name rule (read carefully):
        
              - The site's brand/title name is: "%s"
              - Wherever the ORIGINAL HTML shows a brand or site name (e.g. in the
                logo text, <title> tag, header/nav, footer copyright line, "Welcome
                to X" style headlines, meta tags, or anywhere else the brand
                clearly appears as a name rather than generic copy), replace it
                with EXACTLY this brand name: "%s"
              - Use this exact brand name consistently everywhere it belongs across
                the whole document — do not invent a different brand name and do
                not alter, translate, or stylize the given brand name itself.
              - This exact-match rule applies only to the brand/site name. All other
                copy (headlines, taglines, game names, FAQ, etc.) should still be
                freshly generated as described below.
        
              Content rules:
        
              - Before returning, scan every text node and attribute listed above.
                If any of them is still in a language other than %s, translate it.
                Do not leave any original-language text in the output.
              - Generate completely new, randomized casino/slots-themed copy for
                every text element other than the brand name: headlines, taglines,
                feature descriptions, game names, game tags/categories, stats,
                jackpot copy, FAQ questions and answers, CTA copy, footer text, etc.
              - Match the tone and length roughly to what's already there — a
                headline stays headline-length, a short button label stays short,
                a paragraph stays a similar length — so the existing layout doesn't
                break or overflow.
              - Keep the copy premium, professional, and plausible for a real
                online casino/slots brand. Avoid generic filler like "Lorem ipsum"
                or placeholder-sounding text.
              - Do not reuse the exact game names or copy from the original HTML —
                invent new ones. The only text that MUST stay fixed and reused
                everywhere is the brand name given above.
        
              Important:
        
              - The HTML structure, tags, classes, and IDs must remain completely
                unchanged.
              - Only text content and the text-bearing attributes listed above may
                change.
              - Do not explain.
              - Return only HTML.
        
              Existing HTML (structure, styles, and current content):
        
              %s
        """;

    public static String buildHtmlChangePrompt(Rules rules) {
        return buildMainRule(rules).concat(HTML_CHANGE_TEMPLATE.formatted(rules.title, rules.title, rules.language, rules.content));
    }

    private static String buildMainRule(Rules rules) {
        return MAIN_RULE.concat("\n").concat(REQUEST_TEMPLATE.formatted(rules.language, rules.content)).concat("\n");
    }

    public record Rules(String title, String language, String content) {

    }
}
