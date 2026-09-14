package com.elias.site_generation.adapter.spring.in.exception.utils;

import com.elias.site_generation.shared.exception.ExceptionResponse;
import org.springframework.http.HttpStatus;

public final class HandlerUtils {

    public static ExceptionResponse buildInternalErrorBody(String path) {
        return ExceptionResponse.builder()
                .message("Internal server error.")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(path)
                .build();
    }

}
