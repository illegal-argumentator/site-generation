package com.elias.site_generation.port.theme;

import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.shared.response.ResponseBody;

public interface ThemeDeploymentPort {

    ResponseBody<Object> deploy(Theme theme);

}
