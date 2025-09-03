package com.my.securityTest.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String mainPage(Model model) {
        // 현재 로그인된 사용자 정보를 확인
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        model.addAttribute("user", username);
        return "main";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }
}
