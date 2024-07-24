package com.wskang.trip.repository;

import com.wskang.trip.entity.DailyPlan;
import com.wskang.trip.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
    // 특정 plan에 속하는 모든 DailyPlan 엔티티 목록을 조회하는 메소드
    List<DailyPlan> findByPlan(Plan plan);
}
