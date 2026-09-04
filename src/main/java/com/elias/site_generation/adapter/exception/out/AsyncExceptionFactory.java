package com.elias.site_generation.adapter.exception.out;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
final class AsyncExceptionFactory {

    private final List<ExceptionHandlerStrategy> strategies;

    ExceptionHandlerStrategy getStrategy(Throwable ex) {
        return strategies.stream().filter(strategy -> strategy.getType().isInstance(ex))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Async exception strategy not found."));
    }
}
