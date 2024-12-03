package com.stroygen.urdisauthservice.common.handler;


import com.stroygen.urdisauthservice.dto.MemberDto;
import com.stroygen.urdisauthservice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();
        String email = user.getAttribute("email");
        MemberDto memberDto = authService.getMemberByEmail(email);
        if (memberDto == null) {
            response.sendRedirect("/members/register");
            return;
        }
        authService.generateJwtToken(response, memberDto);
        response.sendRedirect("/login/success");
    }
}
