package com.wskang.trip.service.Impl;

import com.wskang.trip.dto.SubPlanDto;
import com.wskang.trip.entity.DailyPlan;
import com.wskang.trip.entity.SubPlan;
import com.wskang.trip.exception.BadRequestException;
import com.wskang.trip.exception.ResourceNotFoundException;
import com.wskang.trip.repository.DailyPlanRepository;
import com.wskang.trip.repository.SubPlanRepository;
import com.wskang.trip.service.SubPlanService;
import com.wskang.trip.utils.ValidateTimeUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubPlanServiceImpl implements SubPlanService {

    // 의존성 주입
    private DailyPlanRepository dailyPlanRepository;
    private SubPlanRepository subPlanRepository;
    private ModelMapper modelMapper; // spring bean 이며 생성자를 통해 의존성 주입 진행

    @Override
    public SubPlanDto addSubPlan(SubPlanDto subPlanDto) {

//        // startTime이 유효한 {HH:MM} 시간 formatting 인지 검증
//        if ( !ValidateTimeUtil.isValidTime(subPlanDto.getStartTime()) )
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "startTime has a invalid time formatting in {HH:MM} : " + subPlanDto.getStartTime());
//
//        // endTime이 유효한 {HH:MM} 시간 formatting 인지 검증
//        if ( !ValidateTimeUtil.isValidTime(subPlanDto.getEndTime()) )
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "endTime has a invalid time formatting in {HH:MM} : " + subPlanDto.getEndTime());

        // convert Dto into Jpa entity
        SubPlan subPlan = modelMapper.map(subPlanDto, SubPlan.class);

        // Jpa entity
        SubPlan savedSubPlan = subPlanRepository.save(subPlan);

        // return saved Jpa entity into Dto
        return modelMapper.map(savedSubPlan, SubPlanDto.class);
    }

    // subId를 통해 특정 SubPlan return
    @Override
    public SubPlanDto getSubPlan(Long subId) {
        SubPlan subPlan = subPlanRepository.findById(subId)
                .orElseThrow(() -> new ResourceNotFoundException("Sub Plan not found with id:" + subId));

        return modelMapper.map(subPlan, SubPlanDto.class);
    }

    // 특정 dailyId 갖는 Daily Plan의 모든 sub Plan return
    @Override
    public List<SubPlanDto> getAllSubPlans(Long dailyId) {
        // 먼저 dailyId 기본키로 갖는 dailyPlan 엔티티를 가져온다
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyId)
                .orElseThrow(() -> new ResourceNotFoundException("Daily Plan not found with id:" + dailyId));

        // 해당 dailyPlan에 해당하는 모든 subPlan 엔티티를 가져온다
        List<SubPlan> subPlans = subPlanRepository.findByDailyPlan(dailyPlan);

        return subPlans.stream().map((subPlan) -> modelMapper.map(subPlan, SubPlanDto.class))
                .collect(Collectors.toList());
    }

    // subId를 통해 특정 subPlan 정보를 update
    @Override
    public SubPlanDto updateSubPlan(SubPlanDto subPlanDto, Long subId) {
        // update 하려는 subPlan의 id가 유효한지 검사
        SubPlan subPlan = subPlanRepository.findById(subId)
                .orElseThrow(() -> new ResourceNotFoundException("Sub Plan not found with id:" + subId));

//        // startTime이 유효한 {HH:MM} 시간 formatting 인지 검증
//        if ( !ValidateTimeUtil.isValidTime(subPlanDto.getStartTime()) )
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "startTime has a invalid time formatting in {HH:MM} : " + subPlanDto.getStartTime());
//
//        // endTime이 유효한 {HH:MM} 시간 formatting 인지 검증
//        if ( !ValidateTimeUtil.isValidTime(subPlanDto.getEndTime()) )
//            throw new BadRequestException(HttpStatus.BAD_REQUEST, "endTime has a invalid time formatting in {HH:MM} : " + subPlanDto.getEndTime());

        // update를 위해 새로운 값을 쓴다
        subPlan.setType(subPlanDto.getType());
        subPlan.setStartTime(subPlanDto.getStartTime());
        subPlan.setEndTime(subPlanDto.getEndTime());
        subPlan.setLocation(subPlanDto.getLocation());
        subPlan.setVehicle(subPlanDto.getVehicle());
        subPlan.setMoney(subPlanDto.getMoney());
        subPlan.setCurrency(subPlanDto.getCurrency());
        subPlan.setComment(subPlanDto.getComment());
        subPlan.setIsComplete(subPlanDto.getIsComplete());

        SubPlan updatedSubPlan = subPlanRepository.save(subPlan); // 기본키가 동일한 값에 대해 save는 update로 진행함

        return modelMapper.map(updatedSubPlan,SubPlanDto.class);
    }

    // 특정 subPlan 정보 delete
    @Override
    public void deleteSubPlan(Long subId) {
        SubPlan subPlan = subPlanRepository.findById(subId)
                .orElseThrow(() -> new ResourceNotFoundException("Sub Plan not found with id:" + subId));

        subPlanRepository.deleteById(subId);
    }

    // subPlan에서 isComplete를 true로 부분 update
    @Override
    public SubPlanDto completeSubPlan(Long subId) {
        SubPlan subPlan = subPlanRepository.findById(subId)
                .orElseThrow(() -> new ResourceNotFoundException("Sub Plan not found with id:" + subId));

        subPlan.setIsComplete(Boolean.TRUE);

        SubPlan updatedSubPlan = subPlanRepository.save(subPlan);

        return modelMapper.map(updatedSubPlan, SubPlanDto.class);
    }

    // subPlan에서 isComplete를 false로 부분 update
    @Override
    public SubPlanDto inCompleteSubPlan(Long subId) {
        SubPlan subPlan = subPlanRepository.findById(subId)
                .orElseThrow(() -> new ResourceNotFoundException("Sub Plan not found with id:" + subId));

        subPlan.setIsComplete(Boolean.FALSE);

        SubPlan updatedSubPlan = subPlanRepository.save(subPlan);

        return modelMapper.map(updatedSubPlan, SubPlanDto.class);
    }
}
