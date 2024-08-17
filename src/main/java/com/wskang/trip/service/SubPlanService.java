package com.wskang.trip.service;

import com.wskang.trip.dto.SubPlanDto;

import java.util.List;

/**
 * SubPlanService
 *
 * SubPlan 관련 Service 구현을 위한 interface 정의
 *
 */

public interface SubPlanService {
    SubPlanDto addSubPlan(SubPlanDto subPlanDto);

    SubPlanDto getSubPlan(Long id);

    List<SubPlanDto> getAllSubPlans(Long dailyId);

    SubPlanDto updateSubPlan(SubPlanDto subPlanDto, Long id);
    
    void deleteSubPlan(Long id);

    SubPlanDto completeSubPlan(Long id);

    SubPlanDto inCompleteSubPlan(Long id);
}
