package com.elias.site_generation.adapter.theme.out.generation;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.elias.site_generation.adapter.theme.out.prompt.CasinoTitlePromptPolicy.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class TitleGenerationAdapter implements TitleGenerationPort {

    private final AiService aiService;

    @Override
    public String generate() {
        AiRequest request = new AiRequest(TITLE_SYSTEM_PROMPT, TITLE_USER_PROMPT);
        String title = aiService.generate(request);
        log.info("Title successfully generated: {}.", title);
        return title;
    }
}
