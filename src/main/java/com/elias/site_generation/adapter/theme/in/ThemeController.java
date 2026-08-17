package com.elias.site_generation.adapter.theme.in;

import com.elias.site_generation.adapter.theme.out.mapper.MultipartThemeMapper;
import com.elias.site_generation.port.theme.ThemeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/themes")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeUseCase useCase;
    private final MultipartThemeMapper mapper;

    @PostMapping
    public void save(@RequestBody MultipartFile file) {
        useCase.save(mapper.toTheme(file));
    }

}
