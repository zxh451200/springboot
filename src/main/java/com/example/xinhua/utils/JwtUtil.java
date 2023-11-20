package com.example.xinhua.utils;

import java.util.Map;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;

public class JwtUtil {
    private static final String key = "xin";
    private static final String mapKey = "claims";

    public static String getToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim(mapKey, claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                .sign(Algorithm.HMAC256(key));
    }

    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(key))
                .build()
                .verify(token)
                .getClaims()
                .get(mapKey)
                .asMap();
    }

    public static String removeToken() {
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(Algorithm.HMAC256(key));
    }
}
