package com.wskang.trip.service.Impl;

import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.entity.Plan;
import com.wskang.trip.repository.PlanRepository;
import com.wskang.trip.service.PlanService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PlanServiceImpl implements PlanService {

    private PlanRepository planRepository;
    private ModelMapper modelMapper;

    @Override
    public PlanDto addPlan(PlanDto planDto) {
        // convert Dto into Jpa entity
        Plan plan = modelMapper.map(planDto, Plan.class);

        // Jpa entity
        Plan savedPlan = planRepository.save(plan);

        // Convert saved Jpa entity into Dto
        PlanDto savedPlanDto = modelMapper.map(savedPlan, PlanDto.class);

        return savedPlanDto;
    }
}
