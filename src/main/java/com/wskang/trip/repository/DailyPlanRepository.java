package com.wskang.trip.repository;

import com.wskang.trip.entity.DailyPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
}
