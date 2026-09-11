package com.elias.site_generation.adapter.exception.out.strategy;

import com.elias.site_generation.adapter.exception.out.ExceptionHandlerStrategy;
import com.elias.site_generation.domain.site.event.SiteCreationFailedEvent;
import com.elias.site_generation.domain.site.exception.SiteGenerationException;
import com.elias.site_generation.domain.site.type.CreationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsyncSiteGenerationExceptionStrategy implements ExceptionHandlerStrategy {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void process(Throwable ex) {
        SiteGenerationException siteEx = (SiteGenerationException) ex;
        eventPublisher.publishEvent(new SiteCreationFailedEvent(siteEx.getSiteId(), siteEx.getMessage(), CreationStatus.FAILED));
    }

    @Override
    public Class<? extends RuntimeException> getType() {
        return SiteGenerationException.class;
    }
}
