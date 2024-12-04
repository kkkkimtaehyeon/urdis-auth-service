package com.stroygen.urdisauthservice.service;

import com.stroygen.urdisauthservice.common.provider.JwtProvider;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, Object> session;

    public void setAccessTokenCookie(HttpServletResponse res, String token) {
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setDomain("/");
        cookie.setHttpOnly(true);
        res.addCookie(cookie);
    }

    public void saveRefreshTokenOnRedis(String accessToken, String refreshToken) {
        session.opsForHash().put("Session", accessToken, refreshToken);
    }
}
