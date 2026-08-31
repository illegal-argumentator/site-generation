package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.website.WebsiteThemeCommandPort;
import com.elias.site_generation.shared.props.HestiaProps;
import com.elias.site_generation.shared.props.WpProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class WordPressThemeCommandAdapter implements WebsiteThemeCommandPort {

    private final WpProps wpProps;
    private final HestiaProps hestiaProps;
    private final RemoteCommandPort remote;

    private static final String UNDERSCORE_PREFIX = "_";
    private static final String DELETE_INDEX_TEMPLATE = "sudo -u %s rm -f /home/%s/web/%s/public_html/index.html";

    @Override
    public void downloadWebsite(String hostname) {
        String command = buildCommand(hostname, "core", "download");
        remote.execute(command);
    }

    @Override
    public void installWebsite(String hostname) {
        String command = buildCommand(
                hostname,
                "core",
                "install",
                "--url=https://" + hostname,
                "--title=my_site",
                "--admin_user=" + wpProps.getUsername(),
                "--admin_password=" + wpProps.getPassword(),
                "--admin_email=admin@" + hostname);

        remote.execute(command);
    }

    @Override
    public void installTheme(String themePath, String hostname) {
        String command = buildCommand(hostname, "theme", "install", themePath);
        remote.execute(command);
    }

    @Override
    public void createConfig(Db db, String hostname) {
        String command = buildCommand(
                hostname,
                "config",
                "create",
                "--dbname=" + addUserUnderscorePrefix(db.name()),
                "--dbuser=" + addUserUnderscorePrefix(db.username()),
                "--dbpass=" + db.password(),
                "--dbhost=localhost"
        );

        remote.execute(command);
    }

    @Override
    public void activateTheme(String name, String hostname) {
        String command = buildCommand(hostname, "theme", "activate", name);
        remote.execute(command);
    }

    @Override
    public void deleteIndex(String hostname) {
        remote.execute(DELETE_INDEX_TEMPLATE.formatted(hestiaProps.getUsername(), hestiaProps.getUsername(), hostname));
    }

    private String buildCommand(String hostname, String... arguments) {
        StringBuilder sb = new StringBuilder()
                .append("sudo -n -u ")
                .append(hestiaProps.getUsername())
                .append(" -H /usr/local/bin/wp ")
                .append("--path=")
                .append(wpProps.buildPath(hestiaProps.getUsername(), hostname));

        for (String argument : arguments) {
            sb.append(" ").append(argument);
        }

        return sb.toString();
    }

    private String addUserUnderscorePrefix(String prop) {
        return hestiaProps.getUsername() + UNDERSCORE_PREFIX + prop;
    }
}