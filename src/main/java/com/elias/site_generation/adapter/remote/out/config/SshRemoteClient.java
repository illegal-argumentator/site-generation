package com.elias.site_generation.adapter.remote.out.config;

import com.elias.site_generation.shared.props.SshProps;
import lombok.RequiredArgsConstructor;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.NamedResource;
import org.apache.sshd.common.util.security.SecurityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;

@Component
@RequiredArgsConstructor
public class SshRemoteClient {

    private final SshClient client;
    private final SshProps props;

    public ClientSession connect() throws IOException {
        var session = client.connect(props.getUsername(), props.getHost(), props.getPort()).verify().getSession();

        try (InputStream inputStream = Files.newInputStream(Path.of(props.getPrivateKey()))) {
            var keyPair = SecurityUtils.loadKeyPairIdentities(
                    null,
                    NamedResource.ofName(Path.of(props.getPrivateKey()).getFileName().toString()),
                    inputStream,
                    null
            ).iterator().next();

            session.addPublicKeyIdentity(keyPair);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        session.auth().verify();
        return session;
    }

}
