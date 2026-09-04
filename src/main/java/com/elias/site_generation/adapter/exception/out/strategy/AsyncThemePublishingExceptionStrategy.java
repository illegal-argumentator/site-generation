package com.elias.site_generation.adapter.exception.out.strategy;

import com.elias.site_generation.adapter.exception.out.ExceptionHandlerStrategy;
import com.elias.site_generation.domain.theme.event.ThemePublishFailedEvent;
import com.elias.site_generation.domain.theme.exception.ThemePublishingException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsyncThemePublishingExceptionStrategy implements ExceptionHandlerStrategy {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void process(Throwable ex) {
        ThemePublishingException themeEx = (ThemePublishingException) ex;
        eventPublisher.publishEvent(new ThemePublishFailedEvent(themeEx.getSiteId(), themeEx.getMessage(), themeEx.getDeployStatus()));
    }

    @Override
    public Class<? extends RuntimeException> getType() {
        return ThemePublishingException.class;
    }
}
