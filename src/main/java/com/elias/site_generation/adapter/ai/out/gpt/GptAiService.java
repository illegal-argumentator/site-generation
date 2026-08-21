package com.elias.site_generation.adapter.ai.out.gpt;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import com.elias.site_generation.adapter.ai.out.exception.AiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GptAiService implements AiService {

    private final ChatClient chatClient;

    @Override
    public String generate(AiRequest request) {
        try {
            return chatClient.prompt()
                    .system(request.systemPrompt())
                    .user(request.userPrompt())
                    .call()
                    .content();
        } catch (Exception e) {
            throw new AiException("Unable to request fetch AI service: %s.".formatted(e.getMessage()));
        }
    }

}
