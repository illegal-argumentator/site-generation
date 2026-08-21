package com.elias.site_generation.adapter.deploy.out;

import com.elias.site_generation.adapter.deploy.out.dto.DeployRequest;
import com.elias.site_generation.adapter.theme.out.DeployServicePort;
import org.springframework.stereotype.Component;

@Component
public class DeployServiceAdapter implements DeployServicePort {

    @Override
    public void deploy(DeployRequest request) {

    }

}
