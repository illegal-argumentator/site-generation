package com.elias.site_generation.adapter.user.out.mapper;

import com.elias.site_generation.adapter.user.out.persistence.PostgresUser;
import com.elias.site_generation.domain.user.User;
import com.elias.site_generation.infrastructure.mapper.MapStructConfig;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(config = MapStructConfig.class)
public interface UserMapper {

    User toUser(PostgresUser entity);
    PostgresUser toEntity(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget PostgresUser entity, User user);


}
