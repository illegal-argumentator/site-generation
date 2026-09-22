package com.elias.site_generation.adapter.theme.in.listener;

import com.elias.site_generation.domain.theme.event.ThemePublishEvent;
import com.elias.site_generation.port.theme.usecase.ThemePublishUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemePublishEventListener {

    private final ThemePublishUseCase useCase;

    @EventListener
    public void listen(ThemePublishEvent event) {
        log.info("Received event for publishing theme for site: {}.", event.site().getId());
        useCase.publish(event.site());
    }
}
