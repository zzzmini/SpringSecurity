package com.my.securityTest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "home"; // home.html
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userOnly")
    @ResponseBody
    public String userOnly() {
        return "로그인 한 사용자만 접근 가능!";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminOnly")
    @ResponseBody
    public String adminOnly() {
        return "관리자 전용 페이지!";
    }
}
