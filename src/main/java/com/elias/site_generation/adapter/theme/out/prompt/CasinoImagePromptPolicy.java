package com.elias.site_generation.adapter.theme.out.prompt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CasinoImagePromptPolicy {

    public static final String CASINO_IMAGE_PROMPT = """
            Generate a completely new and unique promotional thumbnail for an online casino game.
            
            Create original artwork specifically for this game. Every generation must produce a visually unique image. Do not reuse, replicate, or closely imitate previously generated artwork, compositions, characters, scenes, color arrangements, or visual layouts.
            
            The image must look like the official promotional artwork / cover thumbnail of a professionally produced online casino slot game displayed in a casino lobby.
            
            Create a rich, exciting and visually memorable game scene with:
            
            * a strong central focal point
            * recognizable game-specific elements
            * a detailed background
            * cinematic lighting
            * glowing effects and atmospheric particles where appropriate
            * layered depth
            * premium 3D-rendered or high-end illustrated game-art aesthetic
            * vibrant and sophisticated colors
            * strong contrast
            * visually rich composition that remains recognizable at small thumbnail size
            
            The artwork must clearly match the game's title and type.
            
            For example:
            
            * Diamond / luxury games → diamonds, gemstones, gold, luxury, glowing blue/purple atmosphere
            * Classic slots → reels, cherries, bells, coins, lucky sevens, golden casino elements
            * Fantasy games → magical creatures, treasure, mystical environments
            * Space games → planets, stars, cosmic environments, futuristic neon elements
            * Adventure games → temples, treasure, gold coins, ancient artifacts
            * Jackpot games → gold, diamonds, coins, celebratory lighting and an exciting jackpot atmosphere
            
            Important:
            
            * Generate original artwork every time.
            * Do not reuse previous compositions.
            * Do not create a generic casino photograph.
            * Do not create a casino website screenshot.
            * Do not include UI elements.
            * Do not include buttons or menus.
            * Do not include the game title.
            * Do not include any text.
            * Do not include logos or brands.
            * Do not include watermarks.
            * Do not copy existing casino game artwork.
            * Do not imitate a specific existing casino game's artwork.
            
            Image requirements:
            
            * 16:9 landscape composition
            * optimized as a game-card thumbnail
            * premium commercial game-art quality
            * visually engaging even when displayed at a small size
            * original and unique visual composition
            """;

}
