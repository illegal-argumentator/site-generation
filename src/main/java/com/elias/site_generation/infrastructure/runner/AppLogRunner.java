package com.elias.site_generation.infrastructure.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppLogRunner {

    @Value("${app.doc-url}")
    private String APP_DOC_URL;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        log.info("App docs url: {}.", APP_DOC_URL);
    }

}
