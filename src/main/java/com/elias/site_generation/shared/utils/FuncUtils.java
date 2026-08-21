package com.elias.site_generation.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FuncUtils {

    public static void runOrThrow(Runnable action, RuntimeException ex) {
        try {
            action.run();
        } catch (Exception e) {
            throw ex;
        }
    }
}
