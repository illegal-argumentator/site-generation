package com.elias.site_generation.adapter.wordpress.out;

import com.elias.site_generation.port.remote.RemoteCommandPort;
import com.elias.site_generation.port.website.WebsiteThemeQueryPort;
import com.elias.site_generation.shared.props.HestiaProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WordPressThemeQueryAdapter implements WebsiteThemeQueryPort {

    private final HestiaProps hestiaProps;

    private final RemoteCommandPort remoteService;
    private static final String DOMAIN_LIST_COMMAND_TEMPLATE = "sudo /usr/local/hestia/bin/v-list-web-domain %s %s";

    @Override
    public boolean exists(String hostname) {
        try {
            remoteService.execute(DOMAIN_LIST_COMMAND_TEMPLATE.formatted(hestiaProps.getUsername(), hostname));
            return true;
        } catch (Exception e) {
            log.error("Exception while verifying domain existence: {}.", e.getMessage());
            return false;
        }
    }
}
