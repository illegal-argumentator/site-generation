package com.elias.site_generation.adapter.hestia.out;

import com.elias.site_generation.infrastructure.executor.ProcessExecutor;
import com.elias.site_generation.port.host.HostingPort;
import com.elias.site_generation.shared.props.HestiaProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class HestiaHostingAdapter implements HostingPort {

    @Value("${server.hostname}")
    private String hostname;

    private final HestiaProps props;
    private final ProcessExecutor processExecutor;

    @Override
    public void createDomain() {
        processExecutor.execute(List.of(
                "sudo",
                "/usr/local/hestia/bin/v-add-web-domain",
                props.getUsername(),
                hostname
        ));
    }

    @Override
    public void createDb() {
        processExecutor.execute(List.of(
                "sudo",
                "/usr/local/hestia/bin/v-add-database",
                props.getUsername(),
                props.getDbName(),
                props.getDbUser(),
                props.getDbPassword()
        ));
    }

}
