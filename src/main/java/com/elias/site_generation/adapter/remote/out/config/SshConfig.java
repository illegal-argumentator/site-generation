package com.elias.site_generation.adapter.remote.out.config;

import com.elias.site_generation.shared.props.SshProps;
import lombok.RequiredArgsConstructor;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.NamedResource;
import org.apache.sshd.common.util.security.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

@Configuration
@RequiredArgsConstructor
public class SshConfig {

    private final SshProps props;

    @Bean
    public SshClient sshClient() {
        return SshClient.setUpDefaultClient();
    }

    @Bean
    public ClientSession connect(SshClient client) throws IOException, GeneralSecurityException {
        var session = client.connect(props.getUsername(), props.getHost(), props.getPort()).verify().getSession();

        session.addPublicKeyIdentity(SecurityUtils.loadKeyPairIdentities(
                null,
                NamedResource.ofName(Paths.get(props.getPrivateKey()).getFileName().toString()),
                Files.newInputStream(Paths.get(props.getPrivateKey())),
                null
        ).iterator().next());

        session.auth().verify();
        return session;
    }

}
