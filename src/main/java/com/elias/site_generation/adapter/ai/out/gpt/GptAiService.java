package com.elias.site_generation.adapter.ai.out.gpt;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GptAiService implements AiService {

    private final ChatClient chatClient;

    @Override
    public String generate(AiRequest request) {
        return chatClient.prompt()
                .system(request.systemPrompt())
                .user(request.userPrompt())
                .call()
                .content();
    }

}
