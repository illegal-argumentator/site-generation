package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.port.auth.AuthUserPort;
import com.elias.site_generation.port.site.usecase.SiteQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteQueryService implements SiteQueryUseCase {

    private final AuthUserPort authUserPort;

    @Override
    public List<Site> getSites() {
        return authUserPort.getAuthUser().getSites();
    }

}
