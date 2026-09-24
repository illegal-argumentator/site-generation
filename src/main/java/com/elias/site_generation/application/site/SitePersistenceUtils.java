package com.elias.site_generation.application.site;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.site.type.ActiveStatus;
import com.elias.site_generation.domain.site.type.CreationStatus;
import com.elias.site_generation.domain.site.type.DeployStatus;
import com.elias.site_generation.domain.theme.TemplateType;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.port.site.DbGenerationPort;
import com.elias.site_generation.port.site.SiteCommandPort;
import com.elias.site_generation.port.user.UserCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class SitePersistenceUtils {

    private final DbGenerationPort dbGenerationPort;

    private final SiteCommandPort siteCommandPort;

    private final UserCommandPort userCommandPort;

    Site saveCreationInProgress(long siteId, TemplateType type) {
        Site update = Site.builder()
                .creationStatus(CreationStatus.IN_PROGRESS)
                .activeStatus(ActiveStatus.PENDING)
                .deployStatus(DeployStatus.PENDING)
                .type(type)
                .db(dbGenerationPort.generate())
                .build();

        return siteCommandPort.update(siteId, update);
    }

    void saveUserSite(User user, Site site) {
        userCommandPort.update(user.getId(), User.builder().sites(user.collectSites(site)).build());
    }

    Site saveCreated(long siteId, Theme theme) {
        Site forUpdate = Site.builder().creationStatus(CreationStatus.CREATED).theme(theme).build();
        return siteCommandPort.update(siteId, forUpdate);
    }

    Site saveRecreationInProgress(long siteId) {
        Site update = Site.builder().creationStatus(CreationStatus.IN_PROGRESS).activeStatus(ActiveStatus.PENDING).deployStatus(DeployStatus.PENDING).build();
        return siteCommandPort.update(siteId, update);
    }


}
