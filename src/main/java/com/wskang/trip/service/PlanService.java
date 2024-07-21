package com.wskang.trip.service;

import com.wskang.trip.dto.PlanDto;

import java.util.List;

public interface PlanService {
    PlanDto addPlan(PlanDto planDto);

    PlanDto getPlan(Long id);

//    List<PlanDto> getAllPlans();
//
//    PlanDto updatePlan(PlanDto todoDto, Long id);
//
//    void deletePlan(Long id);
//
//    PlanDto completePlan(Long id);
//
//    PlanDto inCompletePlan(Long id);
}
