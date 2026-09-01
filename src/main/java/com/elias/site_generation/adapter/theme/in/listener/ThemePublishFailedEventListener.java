package com.elias.site_generation.adapter.theme.in.listener;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.event.ThemePublishFailedEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemePublishFailedEventListener {

    private final SiteCommandPort siteCommandPort;

    @EventListener
    public void listen(ThemePublishFailedEvent event) {
        log.info("Received event for theme publish failed: {}.", event.id());
        siteCommandPort.update(event.id(), buildBodyForFailed(event));
    }

    private Site buildBodyForFailed(ThemePublishFailedEvent event) {
        return Site.builder().deployStatus(event.deployStatus()).failReason(event.reason()).build();
    }

}
