package com.elias.site_generation.adapter.remote.out;

import com.elias.site_generation.adapter.remote.out.config.SshRemoteClient;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.sftp.client.SftpClient;
import org.apache.sshd.sftp.client.SftpClientFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
class RemoteCommandAdapter implements RemoteCommandPort {

    private final SshRemoteClient client;

    @Override
    public void upload(String localPath, String remotePath) {
        try (SftpClient sftp = SftpClientFactory.instance().createSftpClient(client.connect())) {
            sftp.put(Paths.get(localPath), remotePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file: " + localPath, e);
        }
    }

    @Override
    public void delete(String remotePath) {
        try (SftpClient sftp = SftpClientFactory.instance().createSftpClient(client.connect())) {
            sftp.remove(remotePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete remote file: " + remotePath, e);
        }
    }

    @Override
    public void execute(String command) {
        try (ClientSession session = client.connect()) {
            ChannelExec channel = session.createExecChannel(command);

            var output = new ByteArrayOutputStream();
            var error = new ByteArrayOutputStream();

            channel.setOut(output);
            channel.setErr(error);
            channel.open().verify();
            channel.waitFor(EnumSet.of(ClientChannelEvent.CLOSED), TimeUnit.SECONDS.toMillis(30));

            Integer exit = channel.getExitStatus();

            // 15 code is too many requests 429
            // 4 is conflict 409
            if (exit == null || exit != 0) {
                log.error("Error while executing command: '{}', code: {}, reason: {}.", command, exit, error);
                throw new IllegalStateException(
                        "Exit code: " + exit +
                                "\nstdout:\n" + output +
                                "\nstderr:\n" + error
                );
            }
        } catch (IOException e) {
            log.error("Unexpected exception occurred while executing command: {}.", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
