package com.my.securityTest.controller;

import com.my.securityTest.dto.JoinDto;
import com.my.securityTest.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final JoinService joinService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/join")
    public String join() {
        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProcess(JoinDto joinDto) {
        joinService.joinProcess(joinDto);
        return "redirect:/login";
    }

    @GetMapping("/my/info")
    public String infoPage(Model model) {
        // 현재 로그인된 사용자 정보를 확인
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        model.addAttribute("user", username);
        return "info";
    }
}
