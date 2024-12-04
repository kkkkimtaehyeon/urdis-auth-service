package com.stroygen.urdisauthservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class AuthController {
    @GetMapping("/login/kakao")
    public void loginKakako(HttpServletResponse response) throws IOException {
        response.sendRedirect("/oauth2/authorization/kakao");
        // /login/kakao가 호출된 프론트에 redirect할 url을 전달하고 싶은데 방법을 모르겠음

        System.out.println("로그인 성공");
    }
}
