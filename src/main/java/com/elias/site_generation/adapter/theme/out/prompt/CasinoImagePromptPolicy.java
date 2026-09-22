package com.elias.site_generation.adapter.theme.out.prompt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CasinoImagePromptPolicy {

    public static final String CASINO_IMAGE_PROMPT_TEMPLATE = """
            Generate original promotional artwork for an online casino slot game thumbnail.
            
            Randomly invent a unique game concept and theme for this image — choose ONE fresh direction each time from categories like: classic fruit/lucky-sevens slot, luxury diamonds/gold, mystical fantasy, space/cosmic, ancient adventure/treasure, jackpot/celebration, ocean/pirate, mythology, or another equally fitting casino theme. Do not repeat the same theme, composition, color palette or layout as previous generations — invent a genuinely new combination of theme + colors + focal object + background every time.
            
            Visual style: premium 3D-rendered / high-end illustrated game-art, cinematic lighting, glowing particles, layered depth, vibrant sophisticated colors, strong contrast. Strong central focal point with recognizable elements matching the chosen theme, detailed background.
            
            Composition: 16:9 landscape, optimized to stay visually clear and striking at small thumbnail size. Fully original composition each time.
            
            Strict rules: no text, no titles, no logos, no watermarks, no UI elements, no buttons/menus, no screenshots, not a photo of a real casino, don't copy or imitate existing slot game artwork.
            
            Optional additional context about the site/brand (use only if relevant to visual style, colors or mood — ignore if it's technical, business, or unrelated to visuals): %s
            """;

}
