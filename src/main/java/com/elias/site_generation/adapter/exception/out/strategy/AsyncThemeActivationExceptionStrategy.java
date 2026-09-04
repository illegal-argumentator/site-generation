package com.elias.site_generation.adapter.exception.out.strategy;

import com.elias.site_generation.adapter.exception.out.ExceptionHandlerStrategy;
import com.elias.site_generation.domain.theme.event.ThemeActivationFailedEvent;
import com.elias.site_generation.domain.theme.exception.ThemeActivationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AsyncThemeActivationExceptionStrategy implements ExceptionHandlerStrategy {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void process(Throwable ex) {
        ThemeActivationException themeEx = (ThemeActivationException) ex;
        eventPublisher.publishEvent(new ThemeActivationFailedEvent(themeEx.getSiteId(), themeEx.getMessage(), themeEx.getActiveStatus()));
    }

    @Override
    public Class<? extends RuntimeException> getType() {
        return ThemeActivationException.class;
    }

}
