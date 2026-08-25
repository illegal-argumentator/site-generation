package com.elias.site_generation.adapter.remote.out;

import com.elias.site_generation.adapter.remote.out.config.SshRemoteClient;
import com.elias.site_generation.port.remote.RemoteCommandPort;
import lombok.RequiredArgsConstructor;
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

            System.out.println(channel.getErr());

            if (channel.getExitStatus() != 0) {
                throw new IllegalStateException("Remote command failed: " + error);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
