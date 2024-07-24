package com.wskang.trip.service.Impl;

import com.wskang.trip.dto.DailyPlanDto;
import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.entity.DailyPlan;
import com.wskang.trip.entity.Plan;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.DailyPlanRepository;
import com.wskang.trip.repository.PlanRepository;
import com.wskang.trip.service.DailyPlanService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DailyPlanServiceImpl implements DailyPlanService {

    private DailyPlanRepository dailyPlanRepository;
    private PlanRepository planRepository;
    private ModelMapper modelMapper;

    @Override
    public DailyPlanDto addDailyPlan(DailyPlanDto dailyPlanDto) {
        // convert Dto into Jpa entity
        DailyPlan dailyPlan = modelMapper.map(dailyPlanDto, DailyPlan.class);

        // Jpa entity
        DailyPlan savedDailyPlan = dailyPlanRepository.save(dailyPlan);

        // return saved Jpa entity into Dto
        return modelMapper.map(savedDailyPlan, DailyPlanDto.class);
    }

    // dailyId를 통해 특정 dailyPlan return
    @Override
    public DailyPlanDto getDailyPlan(Long dailyId) {

        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyId)
                .orElseThrow(() -> new ResourceNotFoundException("Daily Plan not found with id:" + dailyId));

        return modelMapper.map(dailyPlan, DailyPlanDto.class);
    }

    // 특정 planId를 갖는 Plan의 모든 dailyPlan return
    @Override
    public List<DailyPlanDto> getAllDailyPlans(Long planId) {

        // 먼저 planId를 기본키로 갖는 plan 엔티티를 가져온다
        Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id:" + planId));

        // 해당 plan에 해당하는 모든 dailyPlan 엔티티를 가져온다
        List<DailyPlan> dailyPlans = dailyPlanRepository.findByPlan(plan);

        return dailyPlans.stream().map((dailyPlan) -> modelMapper.map(dailyPlan, DailyPlanDto.class))
                .collect(Collectors.toList());
    }

    // dailyId를 통해 특정 dailyPlan 정보를 update
    @Override
    public DailyPlanDto updateDailyPlan(DailyPlanDto dailyPlanDto, Long dailyId) {
        // update 하려는 dailyPlan의 id가 유효한지 검사
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyId)
                .orElseThrow(() -> new ResourceNotFoundException("Daily Plan not found with id:" + dailyId));

        // update를 위해 새로운 값을 쓴다
        dailyPlan.setDailyName(dailyPlanDto.getDailyName());

        DailyPlan updatedDailyPlan = dailyPlanRepository.save(dailyPlan); // primary key가 동일한 값에 대해 save는 update로 진행함

        return modelMapper.map(updatedDailyPlan, DailyPlanDto.class);
    }

    // 특정 dailyPlan 정보 delete
    @Override
    public void deleteDailyPlan(Long dailyId) {
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyId)
                .orElseThrow(() -> new ResourceNotFoundException("Daily Plan not found with id:" + dailyId));

        dailyPlanRepository.deleteById(dailyId);
    }
}
