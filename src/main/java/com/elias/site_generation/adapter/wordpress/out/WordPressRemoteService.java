package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.shared.props.HestiaProps;
import com.elias.site_generation.shared.props.WpProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class WordPressRemoteService {

    private final WpProps wpProps;
    private final HestiaProps hestiaProps;

    private final RemoteCommandPort remoteCommandPort;

    public void execute(String hostname, String... arguments) {
        var command = new StringBuilder()
                .append("sudo -n -u ")
                .append(hestiaProps.getUsername())
                .append(" -H /usr/local/bin/wp ")
                .append("--path=")
                .append(wpProps.buildPath(hestiaProps.getUsername(), hostname));

        for (String argument : arguments) {
            command.append(" ").append(argument);
        }

        remoteCommandPort.execute(command.toString());
    }

    public void executeCommand(String... arguments) {
        var command = String.join(" ", arguments);
        remoteCommandPort.execute(command);
    }
}
