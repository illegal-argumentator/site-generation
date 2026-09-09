package com.elias.site_generation.adapter.theme.out.generation.component.image;

import com.elias.site_generation.adapter.ai.out.AiImageService;
import com.elias.site_generation.adapter.theme.out.prompt.CasinoImagePromptPolicy;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.TemplateProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
final class ImageGenerationService implements ImageGenerationPort {

    private final TemplateProps templateProps;
    private final AiImageService aiImageService;

    @Override
    public Map<String, byte[]> generate(byte[] html) {
        log.info("Started images generation.");

        List<CompletableFuture<byte[]>> images = new ArrayList<>();
        Document parsedHtml = Jsoup.parse(new String(html));

        Elements imageElements = parsedHtml.select(templateProps.getImagesClass());
        if (imageElements.isEmpty()) return Map.of();

        imageElements.forEach(_ -> images.add(generateAsync()));

        CompletableFuture.allOf(images.toArray(CompletableFuture[]::new)).join();
        List<byte[]> files = images.stream().map(CompletableFuture::join).toList();

        return files.stream().collect(Collectors.toMap(_ -> generateOriginalImageName(), Function.identity()));
    }

    private CompletableFuture<byte[]> generateAsync() {
        return CompletableFuture.supplyAsync(() -> aiImageService.generate(CasinoImagePromptPolicy.CASINO_IMAGE_PROMPT));
    }

    private String generateOriginalImageName() {
        return UUID.randomUUID() + FileUtils.PNG_FORMAT;
    }
}
