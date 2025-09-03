package com.my.securityTest.service;

import com.my.securityTest.dto.JoinDto;
import com.my.securityTest.dto.UserRole;
import com.my.securityTest.entity.UserEntity;
import com.my.securityTest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {
        // 1. 받은 Dto 안에 username 을 가진 회원이 존재하는지 확인
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());
        if (isUser) {
            return;
        }
        // 2. 없으면 비밀번호를 암호화 해서 저장하기
        UserEntity newUser = new UserEntity();
        newUser.setUsername(joinDto.getUsername());
        // 비밀번호를 암호화 해서 엔티티에 넣자
        newUser.setPassword(bCryptPasswordEncoder
                .encode(joinDto.getPassword()));
        // Role 추가
        newUser.setRole(UserRole.ROLE_USER);
        // 저장
        userRepository.save(newUser);
    }
}
