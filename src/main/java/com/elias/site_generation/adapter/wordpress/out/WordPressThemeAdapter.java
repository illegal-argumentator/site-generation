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
    public void installWebsite() {
        remoteCommandPort.execute(
                String.format(
                        "sudo -u %s -H wp --path=%s core install " +
                                "--url=https://%s " +
                                "--title='My Site' " +
                                "--admin_user=admin " +
                                "--admin_password='Admin123!' " +
                                "--admin_email=admin@%s",
                        props.getUsername(),
                        wordpressPath,
                        hostname,
                        hostname
                )
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
                .append("sudo -u ")
                .append(props.getUsername())
                .append(" -H wp ")
                .append("--path=")
                .append(wordpressPath);

        for (String argument : arguments) {
            command.append(" ").append(argument);
        }

        remoteCommandPort.execute(command.toString());
    }
}
