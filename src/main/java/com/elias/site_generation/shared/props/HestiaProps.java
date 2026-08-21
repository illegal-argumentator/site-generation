package com.elias.site_generation.shared.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "hestia")
public class HestiaProps {

    private String username;

    private String dbName;
    private String dbUser;
    private String dbPassword;

}
