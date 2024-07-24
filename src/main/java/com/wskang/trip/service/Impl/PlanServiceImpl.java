package com.wskang.trip.service.Impl;

import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.entity.Plan;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.PlanRepository;
import com.wskang.trip.service.PlanService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlanServiceImpl implements PlanService {

    // 의존성 주입
    private PlanRepository planRepository;
    private ModelMapper modelMapper; // spring bean 이며 생성자를 통해 의존성 주입 진행

    @Override
    public PlanDto addPlan(PlanDto planDto) {
        // convert Dto into Jpa entity
        Plan plan = modelMapper.map(planDto, Plan.class);

        // Jpa entity
        Plan savedPlan = planRepository.save(plan);

        // return saved Jpa entity into Dto
        return modelMapper.map(savedPlan, PlanDto.class);
    }

    @Override
    public PlanDto getPlan(Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id:" + id));

        return modelMapper.map(plan, PlanDto.class);
    }

    @Override
    public List<PlanDto> getAllPlans() {
        List<Plan> plans = planRepository.findAll();

        return plans.stream().map((plan) -> modelMapper.map(plan, PlanDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PlanDto updatePlan(PlanDto planDto, Long id) {

        // update 하려는 plan의 id가 유효한지 검사
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id:" + id));

        // update를 위해 새로운 값을 쓴다
        plan.setPlanName(planDto.getPlanName());

        Plan updatedPlan = planRepository.save(plan); // primary key가 동일한 값에 대해 save는 update로 진행함

        return modelMapper.map(updatedPlan, PlanDto.class);
    }

    @Override
    public void deletePlan(Long id) {

        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id:" + id));

        planRepository.deleteById(id);
    }
}
