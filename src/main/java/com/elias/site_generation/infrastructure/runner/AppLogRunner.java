package com.elias.site_generation.infrastructure.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppLogRunner {

    @EventListener(ApplicationReadyEvent.class)
    public void run() {

    }

}
