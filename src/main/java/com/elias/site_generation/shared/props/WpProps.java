package com.elias.site_generation.shared.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "wp")
public class WpProps {

    private static final String PATH_TEMPLATE = "/home/%s/web/%s/public_html";

    private String path;
    private String username;
    private String password;

    public String buildPath(String hestiaUser, String hostname) {
        return PATH_TEMPLATE.formatted(hestiaUser, hostname);
    }

}
