package com.wskang.trip.service;

import com.wskang.trip.dto.DailyPlanDto;

import java.util.List;

/**
 * DailyPlanService
 *
 * DailyPlan 관련 Service 구현을 위한 interface 정의
 *
 */

public interface DailyPlanService {
    DailyPlanDto addDailyPlan(DailyPlanDto dailyPlanDto);

    DailyPlanDto getDailyPlan(Long id);

    List<DailyPlanDto> getAllDailyPlans(Long planId);

    DailyPlanDto updateDailyPlan(DailyPlanDto dailyPlanDto, Long id);

    void deleteDailyPlan(Long id);
}
