package com.wskang.trip.repository;

import com.wskang.trip.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * RoleRepository
 *
 * Role 관련된 Repository 정의
 *
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    // 특정 이름을 갖는 role 반환
    Optional<Role> findByRoleName(String roleName);
}
