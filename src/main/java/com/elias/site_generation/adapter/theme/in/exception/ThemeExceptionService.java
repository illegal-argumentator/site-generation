package com.elias.site_generation.adapter.theme.in.exception;

import com.elias.site_generation.domain.site.event.SiteCreationFailedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ThemeExceptionService {

    private final ApplicationEventPublisher eventPublisher;

    public void publishSiteCreationFailed(long siteId, String reason) {
        eventPublisher.publishEvent(new SiteCreationFailedEvent(siteId, reason));
    }

}
