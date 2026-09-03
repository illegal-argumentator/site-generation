package com.elias.site_generation;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.site.SiteQueryPort;
import com.elias.site_generation.port.user.UserCommandPort;
import com.elias.site_generation.port.user.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final SiteQueryPort siteQueryPort;
    private final UserQueryPort userQueryPort;
    private final UserCommandPort userCommandPort;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userQueryPort.findByEmail("oleksandr.melnyk@data-ox.com");
        Site site = siteQueryPort.findById(1);
        List<Site> sites = user.collectSites(site);
        userCommandPort.update(user.getId(), User.builder().sites(sites).build());
    }
}
