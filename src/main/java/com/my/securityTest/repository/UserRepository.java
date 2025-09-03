package com.my.securityTest.repository;

import com.my.securityTest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 아이디가 존재하는지 확인하는 쿼리메서드
    boolean existsByUsername(String username);

    // username으로 검색 한 결과 리턴
    UserEntity findByUsername(String username);
}
