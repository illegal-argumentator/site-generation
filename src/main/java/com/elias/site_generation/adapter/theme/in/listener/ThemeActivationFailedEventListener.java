package com.elias.site_generation.adapter.theme.in.listener;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.event.ThemeActivationFailedEvent;
import com.elias.site_generation.domain.theme.event.ThemePublishFailedEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemeActivationFailedEventListener {

    private final SiteCommandPort siteCommandPort;

    @EventListener
    public void listen(ThemeActivationFailedEvent event) {
        log.info("Received event for theme activation failed: {}.", event.id());
        siteCommandPort.update(event.id(), buildBodyForFailed(event));
    }

    private Site buildBodyForFailed(ThemeActivationFailedEvent event) {
        return Site.builder().activeStatus(event.activeStatus()).failReason(event.reason()).build();
    }

}
