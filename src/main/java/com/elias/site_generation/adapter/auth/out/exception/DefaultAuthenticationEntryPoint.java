package com.elias.site_generation.adapter.auth.out.exception;

import com.elias.site_generation.shared.exception.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class DefaultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.warn("Handling authentication exception: {}", exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .code(HttpServletResponse.SC_UNAUTHORIZED)
                .path(request.getRequestURI())
                .message("Unauthorized.")
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(exceptionResponse));
    }

}