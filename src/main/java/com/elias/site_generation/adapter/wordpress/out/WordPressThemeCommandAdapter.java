package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.domain.site.nested.Db;
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
    private final WordPressRemoteService remoteService;

    private static final String UNDERSCORE_PREFIX = "_";

    @Override
    public void downloadWebsite(String hostname) {
        remoteService.execute(
                hostname,
                "core",
                "download"
        );
    }

    @Override
    public void installWebsite(String hostname) {
        remoteService.execute(
                hostname,
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
    public void installTheme(String themePath, String hostname) {
        remoteService.execute(
                hostname,
                "theme",
                "install",
                themePath
        );
    }

    @Override
    public void createConfig(Db db, String hostname) {
        remoteService.execute(
                hostname,
                "config",
                "create",
                "--dbname=" + addUserUnderscorePrefix(db.name()),
                "--dbuser=" + addUserUnderscorePrefix(db.username()),
                "--dbpass=" + db.password(),
                "--dbhost=localhost"
        );
    }

    @Override
    public void activateTheme(String name, String hostname) {
        remoteService.execute(
                hostname,
                "theme",
                "activate",
                name
        );
    }

    @Override
    public void deleteIndex(String hostname) {
        remoteService.execute(
                hostname,
                "sudo",
                "-u",
                hestiaProps.getUsername(),
                "rm",
                "-f",
                wpProps.buildPath(hestiaProps.getUsername(), hostname) + "/index.html"
        );
    }

    private String addUserUnderscorePrefix(String prop) {
        return hestiaProps.getUsername() + UNDERSCORE_PREFIX + prop;
    }
}