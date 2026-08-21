package com.elias.site_generation.shared.response;

public record ResponseBody<T>(
        T data,
        int code,
        String message
) {

    private static final int OK = 200, REDIRECT = 300;

    public static <T> ResponseBody<T> from(T data) {
        return new ResponseBody<>(data, OK, null);
    }

    public static <T> ResponseBody<T> fail(int code, String message) {
        return new ResponseBody<>(null, code, message);
    }

    public boolean isSuccessful() {
        return code >= OK && code < REDIRECT;
    }

}
