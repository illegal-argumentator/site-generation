package com.elias.site_generation.adapter.theme.in.listener;

import com.elias.site_generation.domain.theme.event.ThemePostDeployEvent;
import com.elias.site_generation.port.theme.usecase.ThemePostDeployUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemePostDeployEventListener {

    private final ThemePostDeployUseCase useCase;

    @EventListener
    public void listen(ThemePostDeployEvent event) {
        log.info("Received event for post deploy theme for site: {}.", event.site().getId());
        useCase.deploy(event.site());
    }

}
