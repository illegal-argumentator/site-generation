package com.elias.site_generation.adapter.ai.out.ollama;

import com.elias.site_generation.adapter.ai.out.AiService;
import com.elias.site_generation.adapter.ai.out.dto.AiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OllamaAiService implements AiService {

    private final ChatModel chatModel;

    @Override
    public String generate(AiRequest request) {
        Prompt prompt = new Prompt(List.of(
                new SystemMessage(request.systemPrompt()),
                new UserMessage(request.userPrompt())
        ));

        return Objects.requireNonNull(chatModel.call(prompt).getResult()).getOutput().getText();
    }

}
