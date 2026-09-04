package com.elias.site_generation.shared.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "endpoints.unauthenticated")
public class PathProps {

    private Set<String> auth;
    private Set<String> general;

    public String[] allPaths() {
        return Stream.of(general, auth)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .toArray(String[]::new);
    }

}