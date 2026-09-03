package com.elias.site_generation;

import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.port.website.WebsiteTemplateSlugQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRuner implements ApplicationRunner {

    private final WebsiteTemplateSlugQueryPort websiteTemplateSlugQueryPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String slug = websiteTemplateSlugQueryPort.getSlug(TemplateType.LUCKY_CASINO);
        System.out.println(slug);
    }
}
