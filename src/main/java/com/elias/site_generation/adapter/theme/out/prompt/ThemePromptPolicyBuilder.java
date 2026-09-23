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

    private static final String CUSTOM_EDIT_TEMPLATE = """
            You are editing ONE EXISTING PAGE of a website on the user's explicit
            request below. The page already has its final structure, styling, and
            content — your job is to apply ONLY the requested change and leave
            everything else byte-for-byte the same.

            USER REQUEST (apply this, and only this):

            %s

            Before making any change, detect from the existing file itself:

            - LANGUAGE: the language already used in the page's visible text
              (headings, paragraphs, buttons, etc.). Any new or edited text you
              write must be in this same language, unless the user's request
              explicitly asks to translate or change the language.
            - BRAND / TITLE NAME: the site's existing brand or site name, as it
              already appears in the page (e.g. logo text, <title> tag,
              header/nav, footer copyright line, meta tags). If your edit touches
              any place where this brand name appears, keep it EXACTLY as it
              already is — do not invent, translate, or stylize a different
              brand name, unless the user's request explicitly asks to change
              the brand name itself.

            How to interpret the request:

            - First, classify what the user is asking for: a TEXT change, a STYLE
              change, a STRUCTURE change, or a combination. Base every decision
              below on that classification.
            - TEXT change (wording, numbers, labels, alt text, aria-labels,
              placeholder text, headline/paragraph content): edit only the
              relevant text nodes / text-bearing attributes, in the detected
              language. Do not touch tags, classes, IDs, attributes, or the
              <style> block.
            - STYLE change (colors, fonts, spacing, sizes, layout look, hover
              effects, animations, responsiveness tweaks): edit only CSS — inside
              the existing <style> element if present, or add/extend inline
              style / a <style> block only if the file has no other way to hold
              CSS. Do not add, remove, rename, or reorder any HTML elements,
              classes, or IDs, and do not change any text content.
            - STRUCTURE change (add/remove/move an element, add a new section,
              change how many items repeat, change element nesting): make the
              MINIMAL structural edit needed to satisfy the request, and nothing
              more. Do not use a structural change as an excuse to also rewrite
              nearby text or styles unless the user asked for that too. Any new
              text introduced by a structural change must match the detected
              language and, where relevant, reuse the detected brand name.
            - If the request is ambiguous about scope (e.g. "make it more
              modern"), interpret it narrowly — prefer a style-only change over a
              structural one — and apply it only to the specific element(s) the
              request clearly points to, not the whole page.
            - If the request names a specific element, section, or area
              ("the hero title", "the submit button", "the footer"), touch only
              that element and its direct children. Everything outside the named
              scope must remain byte-for-byte identical to the input.

            Hard constraints (apply regardless of what the user asked, unless the
            user's request explicitly and unambiguously overrides one of them):

            1. Never change JavaScript bindings, event handlers, or inline
               scripts.
            2. Never remove or break forms or their functional attributes
               (action, method, name, required, etc.).
            3. Never remove WordPress placeholders / template tags / shortcodes.
            4. Never change href, src, or other functional attribute values.
            5. Never remove or rename classes/IDs that are not the direct target
               of the request.
            6. Preserve accessibility (roles, aria-* attributes, alt text
               validity) unless the request specifically asks to change it.
            7. Preserve responsive behavior — do not remove media queries or
               responsive classes as a side effect of a style edit.
            8. Keep the HTML and CSS valid.
            9. Do not add explanations or comments about what you changed.
            10. Return the COMPLETE updated file — not a diff, not just the
                changed fragment.
            11. Any part of the file not covered by the user's request must be
                returned exactly as given, character-for-character.

            Existing file (structure, styles, and current content — this is the
            source of truth for the language, the brand name, and everything you
            are NOT asked to change):

            %s
            """;

    public static String buildCustomEditPrompt(String content, String page) {
        return CUSTOM_EDIT_TEMPLATE.formatted(content, page);
    }

    public static String buildHtmlChangePrompt(Rules rules) {
        return buildMainRule(rules).concat(HTML_CHANGE_TEMPLATE.formatted(rules.title, rules.title, rules.language, rules.content));
    }

    private static String buildMainRule(Rules rules) {
        return MAIN_RULE.concat("\n").concat(REQUEST_TEMPLATE.formatted(rules.language, rules.content)).concat("\n");
    }

    public record Rules(String title, String language, String content) {

    }
}
