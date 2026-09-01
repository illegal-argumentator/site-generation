package com.elias.site_generation.adapter.site.in;

import com.elias.site_generation.adapter.site.in.dto.SitesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sites")
@RequiredArgsConstructor
public class SiteQueryController {

    @GetMapping
    public ResponseEntity<SitesResponse> getSites() {
        return ResponseEntity.ok(new SitesResponse(List.of()));
    }

}
