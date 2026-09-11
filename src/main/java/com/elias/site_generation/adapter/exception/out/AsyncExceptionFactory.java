package com.elias.site_generation.adapter.exception.out;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
final class AsyncExceptionFactory {

    private final List<ExceptionHandlerStrategy> strategies;

    ExceptionHandlerStrategy getStrategy(Throwable ex) {
        return strategies.stream().filter(strategy -> strategy.getType().isInstance(ex))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Unhandled exception occurred: {}.", ex.getMessage());
                    return new IllegalArgumentException("Async exception strategy not found.");
                });
    }
}
