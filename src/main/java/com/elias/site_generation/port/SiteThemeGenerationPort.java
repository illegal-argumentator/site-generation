package com.elias.site_generation.port;

import com.elias.site_generation.domain.site.Site;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.shared.response.ResponseBody;

public interface SiteThemeGenerationPort {

    ResponseBody<Theme> generate(Site site);

}
