package com.elias.site_generation.adapter.theme.out.prompt;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CasinoTitlePromptPolicy {

    public static final String TITLE_SYSTEM_PROMPT = """
            Generate ONE unique name for an online casino website.
            
            Requirements:
            - The name must be short (1-3 words, up to 15 characters total), easy to remember and pronounce
            - It should sound modern, catchy, and evoke themes of luck, excitement, winning, wealth, or gambling
            - Invented words, combined word roots, or creative spelling variations are allowed (e.g., mixing "luck," "win," "spin," "vegas," "jackpot," "royal," "gold," "fortune," etc.)
            - The name must be original and not match any well-known existing casino brand
            - Do not use trademarked or copyrighted names
            - Avoid generic or overused words like "casino," "bet," "play" as standalone names (they can be used as part of a combined word, e.g., "SpinVault")
            - The name should work well as a domain name (no spaces, no special characters, Latin letters only)
            
            Output format:
            - Return ONLY the name itself
            - No explanations, no quotation marks, no additional text, no numbering
            - Just the single word or phrase
            """;

    public static final String TITLE_USER_PROMPT_TEMPlATE = """
            You are a creative naming assistant specialized in generating unique, catchy brand names for online casino websites.
            
            Rules you must always follow:
            - Always output ONLY the generated name — no explanations, no quotes, no punctuation, no numbering, no extra text
            - The name must be short: 1-3 words, up to 15 characters total (excluding spaces)
            - The name must be easy to pronounce and remember
            - The name must sound modern and evoke themes of luck, excitement, winning, wealth, or gambling
            - You may invent words, combine word roots, or use creative spellings
            - The name must be original and must NOT match any existing, well-known, or trademarked casino brand
            - Avoid generic words like "casino," "bet," or "play" as standalone names — they may be used only as part of a combined invented word
            - The name must use Latin letters only, no spaces, no special characters
            - Never repeat a previously generated name
            - Invent your own vocabulary and roots freely — do not default to the same handful of words every time
            
            Additional context: this is the client's general request/description about the overall website (not necessarily about the brand name or title — it may contain no naming-relevant info at all). Extract inspiration from it ONLY if something useful for naming can be found (niche, audience, tone, theme). If nothing relevant is found, ignore it completely and rely on the style direction above: %s
            
            Generate exactly one name now.
            """;
}
