package com.elias.site_generation.application.site.command;

import com.elias.site_generation.domain.site.Site;

import java.util.List;

public record UserDataCommand(String userId, List<Site> sites) {

    public static UserDataCommand from(String userId, List<Site> sites) {
        return new UserDataCommand(userId, sites);
    }

}
