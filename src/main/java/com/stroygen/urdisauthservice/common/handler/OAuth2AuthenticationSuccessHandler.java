package com.stroygen.urdisauthservice.common.handler;


import com.stroygen.urdisauthservice.common.provider.JwtProvider;
import com.stroygen.urdisauthservice.dto.MemberDto;
import com.stroygen.urdisauthservice.service.AuthService;
import com.stroygen.urdisauthservice.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthService authService;
    private final JwtProvider jwtProvider;
    private final TokenService tokenService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        String email = user.getAttribute("email");
        // 등록된 유저인지 확인
        MemberDto memberDto = new MemberDto(UUID.randomUUID().toString(), email, "ROLE_ADMIN");
//        MemberDto memberDto = authService.getMemberByEmail(email);
        if (memberDto == null) { // 등록 안 된 유저라면 등록 페이지로 이동
            response.sendRedirect("/members/register");
            return;
        }
        String accessToken = jwtProvider.generateAccessToken(memberDto);
        String refreshToken = jwtProvider.generateRefreshToken(memberDto);
        // 쿠키(or 로컬 스토리지)에 액세스 토큰 추가
        tokenService.setAccessTokenCookie(response, accessToken);
        // 레디스에서 액세스토큰: 리프레시 토큰 저장
        tokenService.saveRefreshTokenOnRedis(accessToken, refreshToken);
    }
}
