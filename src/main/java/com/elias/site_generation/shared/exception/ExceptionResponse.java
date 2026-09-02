package com.elias.site_generation.shared.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
@Builder
public class ExceptionResponse {

    private String message;
    private Map<String, String> errors;
    private int code;
    private String path;
    private final Instant timestamp = Instant.now();

}


