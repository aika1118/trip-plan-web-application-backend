package com.wskang.trip.service;

import com.wskang.trip.dto.PlanDto;

import java.util.List;

public interface PlanService {
    PlanDto addPlan(PlanDto planDto);

    PlanDto getPlan(Long id);

    List<PlanDto> getAllPlans();

    PlanDto updatePlan(PlanDto PlanDto, Long id);

    // 외래키 관계에 의해 plan 삭제 시 해당 planId를 외래키로 갖고 있는 모든 dailyPlan의 정보도 같이 삭제됨
    void deletePlan(Long id);

}
