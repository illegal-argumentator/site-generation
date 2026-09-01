package com.elias.site_generation.adapter.site.in.exception;

import com.elias.site_generation.domain.site.event.SiteCreationFailedEvent;
import com.elias.site_generation.domain.site.type.CreationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SiteExceptionService {

    private final ApplicationEventPublisher eventPublisher;

    public void publishSiteCreationFailed(long siteId, String reason, CreationStatus creationStatus) {
        eventPublisher.publishEvent(new SiteCreationFailedEvent(siteId, reason, creationStatus));
    }

}
