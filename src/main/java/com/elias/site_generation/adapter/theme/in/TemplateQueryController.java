package com.elias.site_generation.adapter.theme.in;

import com.elias.site_generation.domain.theme.TemplateType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/templates")
@RequiredArgsConstructor
public class TemplateQueryController {

    @GetMapping
    public ResponseEntity<TemplateType[]> getTemplates() {
        return ResponseEntity.ok(TemplateType.values());
    }

}
