package com.elias.site_generation.adapter.exception.out;

import com.elias.site_generation.port.exception.AsyncExceptionHandlerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AsyncExceptionHandlerAdapter implements AsyncExceptionHandlerPort {

    private final AsyncExceptionFactory factory;

    @Override
    public void handle(Throwable ex) {
        ExceptionHandlerStrategy strategy = factory.getStrategy(ex);
        strategy.process(ex);
    }
}
