package com.wskang.trip.repository;

import com.wskang.trip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository
 *
 * User 관련된 Repository 정의
 *
 */

public interface UserRepository extends JpaRepository<User, Long> {

    // User는 1개만 반환하지만 Optional로 감싸 null 체크가 더 명확하게 이루어지게 함
    Optional<User> findByUsername(String username); // username 기반으로 User 엔티티 찾기

    Boolean existsByUsername(String username); // username에 해당하는 User가 있을지 확인

    Boolean existsByEmail(String email); // email에 해당하는 User가 있을지 확인

    Optional<User> findByUsernameOrEmail(String username, String email); // username 또는 email로 해당하는 User 찾기

}
