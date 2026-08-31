package com.elias.site_generation.adapter.hestia.out;

import com.elias.site_generation.domain.site.nested.Db;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.shared.props.HestiaProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class HestiaHostingAdapter implements HostingPort {

    private final HestiaProps props;
    private final RemoteCommandPort remoteCommandPort;

    @Override
    public void createDomain(String hostname) {
        executeRemote(
                "sudo",
                "/usr/local/hestia/bin/v-add-web-domain",
                props.getUsername(),
                hostname
        );
    }

    @Override
    public void enableSsl(String hostname) {
        executeRemote(
                "sudo",
                "/usr/local/hestia/bin/v-add-letsencrypt-domain",
                props.getUsername(),
                hostname
        );
    }

    @Override
    public void createDb(Db db) {
        executeRemote(
                "sudo",
                "/usr/local/hestia/bin/v-add-database",
                props.getUsername(),
                db.name(),
                db.username(),
                db.password()
        );
    }

    private void executeRemote(String... arguments) {
        var command = String.join(" ", arguments);
        remoteCommandPort.execute(command);
    }
}
