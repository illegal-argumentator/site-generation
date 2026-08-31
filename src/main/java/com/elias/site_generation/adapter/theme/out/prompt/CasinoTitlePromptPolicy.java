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

    public static final String TITLE_USER_PROMPT = """
            You are a creative naming assistant specialized in generating unique, catchy brand names for online casino websites.\s
            
            Rules you must always follow:
            - Always output ONLY the generated name — no explanations, no quotes, no punctuation, no numbering, no extra text
            - The name must be short: 1-3 words, up to 15 characters total (excluding spaces)
            - The name must be easy to pronounce and remember
            - The name must sound modern and evoke themes of luck, excitement, winning, wealth, or gambling
            - You may invent words, combine word roots, or use creative spellings (e.g. mixing "luck," "win," "spin," "vegas," "jackpot," "royal," "gold," "fortune," etc.)
            - The name must be original and must NOT match any existing, well-known, or trademarked casino brand
            - Avoid using generic words like "casino," "bet," or "play" as standalone names — they may be used only as part of a combined word (e.g. "SpinVault")
            - The name must use Latin letters only, no spaces, no special characters, so it works well as a domain name
            - Every time you are asked, generate a NEW name, different from any names previously generated in this conversation
            """;
}
