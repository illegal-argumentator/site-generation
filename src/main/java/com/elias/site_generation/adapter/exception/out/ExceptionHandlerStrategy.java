package com.elias.site_generation.adapter.exception.out;

public interface ExceptionHandlerStrategy {

    void process(Throwable ex);

    Class<? extends RuntimeException> getType();

}
