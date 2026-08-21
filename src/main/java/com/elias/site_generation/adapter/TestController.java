package com.elias.site_generation.adapter;

import com.elias.site_generation.shared.props.FilePathProps;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final FilePathProps props;

    @GetMapping
    void test() {
        System.out.println(Paths.get(props.getThemes()).toAbsolutePath());
        System.out.println(Paths.get(props.getThemes()).toAbsolutePath().normalize().toUri());
    }

}
