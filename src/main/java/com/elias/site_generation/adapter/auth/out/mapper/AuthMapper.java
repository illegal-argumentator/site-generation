package com.elias.site_generation.adapter.auth.out.mapper;

import com.elias.site_generation.adapter.auth.in.dto.AuthRequest;
import com.elias.site_generation.adapter.auth.in.dto.AuthResponse;
import com.elias.site_generation.adapter.auth.in.dto.RefreshRequest;
import com.elias.site_generation.application.auth.command.AuthRequestCommand;
import com.elias.site_generation.application.auth.command.AuthResponseCommand;
import com.elias.site_generation.application.auth.command.RefreshRequestCommand;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapStructConfig.class)
public interface AuthMapper {

    AuthRequestCommand toCommand(AuthRequest request);
    AuthResponse toResponse(AuthResponseCommand command);

    RefreshRequestCommand toCommand(RefreshRequest request);

}
