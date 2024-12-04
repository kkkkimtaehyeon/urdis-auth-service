package com.stroygen.urdisauthservice.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    public static final String SESSION = "RefreshTokens:";
    private final RedisTemplate<String, Object> session;

    public void setAccessTokenCookie(HttpServletResponse res, String token) {
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        res.addCookie(cookie);
    }

    public void saveRefreshTokenOnRedis(String accessToken, String refreshToken) {
        session.opsForHash().put(SESSION, accessToken, refreshToken);
    }
}
