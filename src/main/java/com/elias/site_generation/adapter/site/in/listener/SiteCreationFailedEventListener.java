package com.elias.site_generation.adapter.site.in.listener;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.event.SiteCreationFailedEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SiteCreationFailedEventListener {

    private final SiteCommandPort siteCommandPort;

    @EventListener
    public void listen(SiteCreationFailedEvent event) {
        log.info("Received event for site creation failed: {}, reason: {}", event.id(), event.reason());
        siteCommandPort.update(event.id(), buildBodyForFailed(event));
    }

    private Site buildBodyForFailed(SiteCreationFailedEvent event) {
        return Site.builder().creationStatus(event.creationStatus()).failReason(event.reason()).build();
    }
}
