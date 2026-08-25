package com.elias.site_generation.adapter.remote.out.config;

import com.elias.site_generation.shared.props.SshProps;
import lombok.RequiredArgsConstructor;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.common.NamedResource;
import org.apache.sshd.common.util.security.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyPair;

@Configuration
@RequiredArgsConstructor
public class SshConfig {

    private final SshProps props;

    @Bean(destroyMethod = "stop")
    public SshClient sshClient() {
        var client = SshClient.setUpDefaultClient();
        client.start();
        return client;
    }

    @Bean
    public KeyPair keyPair() {
        try (InputStream inputStream = Files.newInputStream(Path.of(props.getPrivateKey()))) {

            return SecurityUtils.loadKeyPairIdentities(
                    null,
                    NamedResource.ofName(Path.of(props.getPrivateKey()).getFileName().toString()),
                    inputStream,
                    null
            ).iterator().next();

        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
