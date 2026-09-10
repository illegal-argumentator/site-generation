package com.elias.site_generation.shared.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "template")
public class TemplateProps {

    private String indexFile;
    private String imagesClass;
    private String assetsPath;
    private String themesPathTemplate;
    private String assetsOriginPathTemplate;

}
