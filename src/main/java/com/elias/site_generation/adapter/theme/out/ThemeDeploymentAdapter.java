package com.elias.site_generation.adapter.theme.out;

import com.elias.site_generation.adapter.deploy.out.dto.DeployRequest;
import com.elias.site_generation.domain.theme.Theme;
import com.elias.site_generation.port.theme.ThemeDeploymentPort;
import com.elias.site_generation.shared.file.FilePath;
import com.elias.site_generation.shared.file.FileUtils;
import com.elias.site_generation.shared.props.FilePathProps;
import com.elias.site_generation.shared.response.ResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ThemeDeploymentAdapter implements ThemeDeploymentPort {

    @Value("${app.domain}")
    private String APP_DOMAIN;

    private final FilePathProps props;
    private final DeployServicePort deployServicePort;

    @Override
    public ResponseBody<Object> deploy(Theme theme) {
        String originalFilename = FileUtils.buildOriginalFilename(theme.id(), FileUtils.ZIP_FORMAT);
        deployServicePort.deploy(new DeployRequest(theme.name(), APP_DOMAIN, null));
        return new ResponseBody<>(null, 0, null);
    }
}
