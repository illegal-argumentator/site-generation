package com.elias.site_generation.shared.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HostProps {

    @Value("${server.hostname}")
    private String hostname;

    @Bean
    public String hostname() {
        return hostname;
    }

}
