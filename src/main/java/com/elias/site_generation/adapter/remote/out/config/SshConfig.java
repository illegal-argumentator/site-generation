package com.elias.site_generation.adapter.remote.out.config;

import org.apache.sshd.client.SshClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SshConfig {

    @Bean(destroyMethod = "stop")
    public SshClient sshClient() {
        var client = SshClient.setUpDefaultClient();
        client.start();
        return client;
    }

}
