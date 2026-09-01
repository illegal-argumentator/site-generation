package com.elias.site_generation.adapter.theme.in.exception;

import com.elias.site_generation.domain.theme.event.ThemePublishFailedEvent;
import com.elias.site_generation.domain.site.type.DeployStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ThemeExceptionService {

    private final ApplicationEventPublisher eventPublisher;

    public void publishThemePublishFailed(long siteId, String reason, DeployStatus deployStatus) {
        eventPublisher.publishEvent(new ThemePublishFailedEvent(siteId, reason, deployStatus));
    }

}
