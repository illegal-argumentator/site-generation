package com.elias.site_generation.shared.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "ssh")
public class SshProps {

    private String username;
    private String host;
    private int port;
    private String privateKey;

}
