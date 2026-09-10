package com.elias.site_generation.shared.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ExceptionResponse {

    private String message;
    private int code;
    private String path;
    private final Instant timestamp = Instant.now();

}


