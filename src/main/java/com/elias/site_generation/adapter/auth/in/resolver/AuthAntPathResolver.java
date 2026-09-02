package com.elias.site_generation.adapter.auth.in.resolver;

import com.elias.site_generation.shared.props.PathProps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AuthAntPathResolver implements AntPathResolver {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final PathProps props;

    @Override
    public boolean isPermittedPath(String path) {
        System.out.println("--------------");
        System.out.println(path);
        System.out.println(Arrays.toString(props.allPaths()));
        System.out.println(Arrays.stream(props.allPaths())
                .anyMatch(pr -> antPathMatcher.match(pr, path)));
        System.out.println("--------------");
        return Arrays.stream(props.allPaths())
                .anyMatch(pr -> antPathMatcher.match(pr, path));
    }

}
