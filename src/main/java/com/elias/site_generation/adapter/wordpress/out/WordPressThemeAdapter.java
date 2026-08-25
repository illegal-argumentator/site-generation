package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.website.WebsiteThemePort;
import com.elias.site_generation.shared.props.HestiaProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class WordPressThemeAdapter implements WebsiteThemePort {

    @Value("${wordpress.path}")
    private String wordpressPath;

    @Value("${server.hostname}")
    private String hostname;

    private final HestiaProps props;
    private final RemoteCommandPort remoteCommandPort;

    @Override
    public void downloadWebsite() {
        executeWpCommand(
                "core",
                "download"
        );
    }

    @Override
    public void installWebsite() {
        executeWpCommand(
                "core",
                "install",
                "--url=https://" + hostname,
                "--title=My Site",
                "--admin_user=admin",
                "--admin_password=Admin123!",
                "--admin_email=admin@" + hostname
        );
    }

    @Override
    public void installTheme(String themePath) {
        executeWpCommand(
                "theme",
                "install",
                themePath
        );
    }

    @Override
    public void activateTheme(String name) {
        executeWpCommand(
                "theme",
                "activate",
                name
        );
    }

    @Override
    public void createConfig() {
        executeWpCommand(
                "config",
                "create",
                "--dbname=" + props.getDbName(),
                "--dbuser=" + props.getDbUser(),
                "--dbpass=" + props.getDbPassword(),
                "--dbhost=localhost"
        );
    }

    private void executeWpCommand(String... arguments) {
        var command = new StringBuilder()
                .append("sudo -n -u ")
                .append(props.getUsername())
                .append(" -H /usr/local/bin/wp ")
                .append("--path=")
                .append(wordpressPath);

        for (String argument : arguments) {
            command.append(" ").append(argument);
        }

        remoteCommandPort.execute(command.toString());
    }
}