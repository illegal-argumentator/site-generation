package com.elias.site_generation.adapter.site.in.listener;

import com.elias.site_generation.domain.site.event.SiteActivationEvent;
import com.elias.site_generation.port.theme.ThemeActivationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SiteActivationEventListener {

    private final ThemeActivationUseCase useCase;

    @EventListener
    public void listen(SiteActivationEvent event) {
        log.info("Received event for site activation: {}.", event.hostname());
        useCase.activate(event.themeName(), event.hostname());
    }

}
