package com.wskang.trip.controller;

import com.wskang.trip.dto.SubPlanDto;
import com.wskang.trip.service.SubPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * SubPlanController
 *
 * SubPlan 관련된 REST API 요청을 처리하는 Controller
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping("api/sub-plans")
@AllArgsConstructor
public class SubPlanController {

    private SubPlanService subPlanService;

    // subPlan을 add 하는 REST API
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // SecurityContext 에 해당하는 ROLE 을 소유하고 있을 때만 메서드 호출 허용 (그렇지 않으면 HTTP 403 Forbidden 반환)
    public ResponseEntity<SubPlanDto> addSubPlan(@RequestBody SubPlanDto subPlanDto){
        SubPlanDto savedSubPlanDto = subPlanService.addSubPlan(subPlanDto);
        return new ResponseEntity<>(savedSubPlanDto, HttpStatus.CREATED);
    }

    // 특정 subPlanId에 해당하는 고유한 subPlan을 return 하는 REST API
    @GetMapping("{subId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SubPlanDto> getSubPlan(@PathVariable("subId") Long subId){
        SubPlanDto subPlanDto = subPlanService.getSubPlan(subId);
        return ResponseEntity.ok(subPlanDto);
    }

    // 특정 dailyId를 갖는 dailyPlan의 모든 subPlan을 return 하는 REST API
    @GetMapping("daily-plan/{dailyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SubPlanDto>> getAllSubPlans(@PathVariable("dailyId") Long dailyId){
        List<SubPlanDto> subPlanDtos = subPlanService.getAllSubPlans(dailyId);
        return ResponseEntity.ok(subPlanDtos);
    }

    // subPlan을 update 하는 REST API
    @PutMapping("{subId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<SubPlanDto> updateSubPlan(@RequestBody SubPlanDto subPlanDto, @PathVariable("subId") Long subId){
        SubPlanDto updatedSubPlanDto = subPlanService.updateSubPlan(subPlanDto, subId);
        return ResponseEntity.ok(updatedSubPlanDto);
    }

    // subPlan을 delete 하는 REST API
    @DeleteMapping("{subId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> deleteSubPlan(@PathVariable("subId") Long subId){
        subPlanService.deleteSubPlan(subId);
        return ResponseEntity.ok("Sub Plan deleted Successfully!");
    }

    // subPlan의 isComplete를 true 값으로 부분 update 진행하는 REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{subId}/complete") // 부분적인 리소스를 업데이트하는 점에서 PUT과 차이가 있음
    public ResponseEntity<SubPlanDto> completeSubPlan(@PathVariable("subId") Long subId){
        SubPlanDto updatedSubPlanDto = subPlanService.completeSubPlan(subId);
        return ResponseEntity.ok(updatedSubPlanDto);
    }

    // subPlan의 isComplete를 false 값으로 부분 update 진행하는 REST API
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{subId}/in-complete") // 부분적인 리소스를 업데이트하는 점에서 PUT과 차이가 있음
    public ResponseEntity<SubPlanDto> inCompleteSubPlan(@PathVariable("subId") Long subId){
        SubPlanDto updatedSubPlanDto = subPlanService.inCompleteSubPlan(subId);
        return ResponseEntity.ok(updatedSubPlanDto);
    }
}
