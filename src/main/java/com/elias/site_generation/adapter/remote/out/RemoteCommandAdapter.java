package com.elias.site_generation.adapter.remote.out;

import com.elias.site_generation.port.remote.RemoteCommandPort;
import lombok.RequiredArgsConstructor;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
class RemoteCommandAdapter implements RemoteCommandPort {

    private final SshClient client;
    private final ClientSession session;

    @Override
    public String execute(String command) {
        client.start();

        try (client) {
            try (session) {
                try (var channel = session.createExecChannel(command)) {

                    var output = new ByteArrayOutputStream();
                    var error = new ByteArrayOutputStream();

                    channel.setOut(output);
                    channel.setErr(error);
                    channel.open().verify();
                    channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), TimeUnit.SECONDS.toMillis(30));

                    if (channel.getExitStatus() != 0) {
                        throw new IllegalStateException("Remote command failed: " + error);
                    }

                    return output.toString(StandardCharsets.UTF_8);
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException("Failed to execute remote command", e);
        }
    }



}
