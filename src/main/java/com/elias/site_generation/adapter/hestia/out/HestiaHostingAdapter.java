package com.elias.site_generation.adapter.hestia.out;

import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.shared.props.HestiaProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

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
    public void createDb(String dbName, String dbPass) {
        executeRemote(
                "sudo",
                "/usr/local/hestia/bin/v-add-database",
                props.getUsername(),
                dbName,
                props.getDbUser(),
                dbPass
        );
    }

    private void executeRemote(String... arguments) {
        var command = Arrays.stream(arguments).map(this::escapeShellArgument).collect(Collectors.joining(" "));
        remoteCommandPort.execute(command);
    }

    private String escapeShellArgument(String argument) {
        return "'" + argument.replace("'", "'\"'\"'") + "'";
    }

}
