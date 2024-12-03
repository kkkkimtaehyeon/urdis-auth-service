package com.stroygen.urdisauthservice.webClient;

import com.stroygen.urdisauthservice.dto.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient
public interface MemberClient {
    @GetMapping("/api/members/email/{email}")
    MemberDto getMemberByEmail(@PathVariable("email") String email);
}
