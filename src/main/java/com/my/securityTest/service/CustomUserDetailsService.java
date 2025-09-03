package com.my.securityTest.service;

import com.my.securityTest.dto.CustomUserDetails;
import com.my.securityTest.entity.UserEntity;
import com.my.securityTest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. username이 DB에 있는지 확인 : 없으면 다시 로그인 보냄
        UserEntity userData = userRepository.findByUsername(username);
        // 비밀번호 체크는 시큐리티가 알아서 해줌.
        // 2. 스프링시큐리티가 관리하는 유저생성
        if (! ObjectUtils.isEmpty(userData)) {
            // 승인된 사용자 명찰 만들기
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
