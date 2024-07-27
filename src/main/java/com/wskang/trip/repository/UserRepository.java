package com.wskang.trip.repository;

import com.wskang.trip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 고유한 username을 통해 User 엔티티 조회
    Optional<User> findByUsername(String username);
}
