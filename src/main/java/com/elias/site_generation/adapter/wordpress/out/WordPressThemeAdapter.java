package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.website.WebsiteThemePort;
import com.elias.site_generation.shared.props.HestiaProps;
import com.elias.site_generation.shared.props.WpProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class WordPressThemeAdapter implements WebsiteThemePort {

    @Value("${wp.path}")
    private String wordpressPath;

    private final HestiaProps hestiaProps;
    private final WpProps wpProps;

    @Value("${server.hostname}")
    private String hostname;

    private static final String UNDERSCORE_PREFIX = "_";

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
                "--title=my_site",
                "--admin_user=" + wpProps.getUsername(),
                "--admin_password=" + wpProps.getPassword(),
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
    public void createConfig(String name, String password) {
        executeWpCommand(
                "config",
                "create",
                "--dbname=" + addUserUnderscorePrefix(name),
                "--dbuser=" + addUserUnderscorePrefix(hestiaProps.getDbUser()),
                "--dbpass=" + password,
                "--dbhost=localhost"
        );
    }

    private String addUserUnderscorePrefix(String prop) {
        return hestiaProps.getUsername() + UNDERSCORE_PREFIX + prop;
    }

    private void executeWpCommand(String... arguments) {
        var command = new StringBuilder()
                .append("sudo -n -u ")
                .append(hestiaProps.getUsername())
                .append(" -H /usr/local/bin/wp ")
                .append("--path=")
                .append(wordpressPath);

        for (String argument : arguments) {
            command.append(" ").append(argument);
        }

        remoteCommandPort.execute(command.toString());
    }
}