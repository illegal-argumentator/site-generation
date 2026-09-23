package com.elias.site_generation.adapter.theme.in.listener;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.event.ThemeDeployFailedEvent;
import com.elias.site_generation.port.site.SiteCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemeDeployFailedEventListener {

    private final SiteCommandPort siteCommandPort;

    @EventListener
    public void listen(ThemeDeployFailedEvent event) {
        log.info("Received event for theme deploy failed: {}.", event.id());
        siteCommandPort.update(event.id(), buildBodyForFailed(event));
    }

    private Site buildBodyForFailed(ThemeDeployFailedEvent event) {
        return Site.builder().deployStatus(event.deployStatus()).failReason(event.reason()).build();
    }

}
