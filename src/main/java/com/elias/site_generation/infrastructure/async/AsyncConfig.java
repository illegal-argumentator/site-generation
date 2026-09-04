package com.elias.site_generation.infrastructure.async;

import com.elias.site_generation.port.exception.AsyncExceptionHandlerPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@Configuration
@RequiredArgsConstructor
public class AsyncConfig implements AsyncConfigurer {

    private final AsyncExceptionHandlerPort asyncExceptionHandlerPort;

    @Override
    public @Nullable AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, _, _) -> {
            log.warn("Handling async exception: {}.", ex.getClass());
            asyncExceptionHandlerPort.handle(ex);
        };
    }

}
