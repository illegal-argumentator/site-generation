package com.elias.site_generation.adapter.site.in.listener;

import com.elias.site_generation.domain.site.event.SiteEditEvent;
import com.elias.site_generation.port.site.usecase.SiteEditUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SiteEditEventListener {

    private final SiteEditUseCase useCase;

    @EventListener
    public void listen(SiteEditEvent event) {
        log.info("Received event for site edit: {}.", event.site().getId());
        useCase.edit(event.content(), event.component(), event.site());
    }

}
