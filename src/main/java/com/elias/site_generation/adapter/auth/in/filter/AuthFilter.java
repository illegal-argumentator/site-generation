package com.elias.site_generation.adapter.auth.in.filter;

import com.elias.site_generation.adapter.auth.out.utils.FilterUtils;
import com.elias.site_generation.adapter.security.out.AuthDecoderPort;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.domain.user.exception.UserNotFoundException;
import com.elias.site_generation.port.user.UserQueryPort;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.elias.site_generation.adapter.auth.out.constants.AuthConstants.AUTHORIZATION_HEADER;
import static com.elias.site_generation.adapter.security.out.utils.JwtUtils.extractTokenWithoutBearer;
import static com.elias.site_generation.adapter.security.out.utils.JwtUtils.isTokenFormatValid;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final UserQueryPort userQueryPort;
    private final AuthDecoderPort authDecoderPort;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext.getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            User user = parseUserFrom(request);
            FilterUtils.addAuthToContext(user, Objects.requireNonNull(user).toRoleNames());
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage());
            SecurityContextHolder.clearContext();
            throw new AuthenticationServiceException("Internal authentication error.");
        }

        filterChain.doFilter(request, response);
    }

    private User parseUserFrom(HttpServletRequest request) {
        String auth = extractAuth(request), email = authDecoderPort.decode(auth);

        try {
            return userQueryPort.findByEmail(email);
        } catch (UserNotFoundException e) {
            throw new AuthenticationCredentialsNotFoundException("User not found.");
        }
    }

    private String extractAuth(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);

        if (isTokenFormatValid(authorization)) {
            return extractTokenWithoutBearer(authorization);
        }

        throw new BadCredentialsException("Invalid token.");
    }
}
