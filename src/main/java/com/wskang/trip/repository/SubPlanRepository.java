package com.wskang.trip.repository;

import com.wskang.trip.entity.DailyPlan;
import com.wskang.trip.entity.Plan;
import com.wskang.trip.entity.SubPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubPlanRepository extends JpaRepository<SubPlan, Long> {
    // 특정 dailyPlan에 속하는 모든 SubPlan 엔티티 목록을 조회하는 메소드
    List<SubPlan> findByDailyPlan(DailyPlan dailyPlan);
}
