package com.elias.site_generation.adapter.theme.in;

import com.elias.site_generation.adapter.theme.out.mapper.MultipartThemeMapper;
import com.elias.site_generation.port.theme.ThemeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/themes")
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeUseCase useCase;
    private final MultipartThemeMapper mapper;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void save(@RequestPart MultipartFile file) {
        useCase.save(mapper.toTheme(file));
    }

}
