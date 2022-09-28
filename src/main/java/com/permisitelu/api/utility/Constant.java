package com.permisitelu.api.utility;

import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class Constant {
    public static final String[] URL_WHITELIST = {
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/webjars/**",
            "/auth/**"
    };
    private static final String APP_KEY = "pPp#Ee3Rm!Sit33lU@MrRw(0)rk5!+=+=+=+=+=+=+=+!";
    public static final Key SECRET_KEY = Keys.hmacShaKeyFor(APP_KEY.getBytes());
}
