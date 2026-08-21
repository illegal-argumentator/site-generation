package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.infrastructure.executor.ProcessExecutor;
import com.elias.site_generation.port.website.WebsiteThemePort;
import com.elias.site_generation.shared.props.HestiaProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class WordPressThemeAdapter implements WebsiteThemePort {

    @Value("${wordpress.path}")
    private String wordpressPath;

    @Value("${server.hostname}")
    private String hostname;

    private final HestiaProps props;
    private final ProcessExecutor processExecutor;

    @Override
    public void installWebsite() {
        processExecutor.execute(List.of(
                "sudo",
                "-u",
                props.getUsername(),
                "-H",
                "wp",
                "--path=" + wordpressPath,
                "core",
                "install",
                "--url=https://" + hostname,
                "--title=My Site",
                "--admin_user=admin",
                "--admin_password=Admin123!",
                "--admin_email=admin@" + hostname
        ));
    }

    @Override
    public void installTheme(String themePath) {
        processExecutor.execute(List.of(
                "sudo",
                "-u",
                props.getUsername(),
                "-H",
                "wp",
                "--path=" + wordpressPath,
                "theme",
                "install",
                themePath
        ));
    }

    @Override
    public void activateTheme(String name) {
        processExecutor.execute(List.of(
                "sudo",
                "-u",
                props.getUsername(),
                "-H",
                "wp",
                "--path=" + wordpressPath,
                "theme",
                "activate",
                name
        ));
    }

    @Override
    public void createConfig() {
        processExecutor.execute(List.of(
                "sudo",
                "-u",
                props.getUsername(),
                "-H",
                "wp",
                "--path=" + wordpressPath,
                "config",
                "create",
                "--dbname=" + props.getDbName(),
                "--dbuser=" + props.getDbUser(),
                "--dbpass=" + props.getDbPassword(),
                "--dbhost=localhost"
        ));
    }
}
