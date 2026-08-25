package com.elias.site_generation.adapter.remote.out.config;

import com.elias.site_generation.shared.props.SshProps;
import lombok.RequiredArgsConstructor;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.KeyPair;

@Component
@RequiredArgsConstructor
public class SshRemoteClient {

    private final SshClient client;
    private final KeyPair keyPair;
    private final SshProps props;

    public ClientSession connect() throws IOException {
        var session = client.connect(props.getUsername(), props.getHost(), props.getPort()).verify().getSession();

        session.addPublicKeyIdentity(keyPair);
        session.auth().verify();

        return session;
    }

}
