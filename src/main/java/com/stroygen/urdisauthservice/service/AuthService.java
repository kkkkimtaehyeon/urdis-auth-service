package com.stroygen.urdisauthservice.service;

import com.stroygen.urdisauthservice.dto.MemberDto;
import com.stroygen.urdisauthservice.webClient.MemberClient;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberClient memberClient;
    public MemberDto getMemberByEmail(String email) {
        return memberClient.getMemberByEmail(email);
    }

    public void generateJwtToken(HttpServletResponse res, MemberDto memberDto) {

    }
}
