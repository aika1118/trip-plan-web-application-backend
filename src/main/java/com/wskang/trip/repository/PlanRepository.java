package com.wskang.trip.repository;

import com.wskang.trip.entity.Plan;
import com.wskang.trip.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    // 특정 user의 모든 plan 엔티티 목록을 조회하는 메소드
    List<Plan> findByUser(User user);
}
