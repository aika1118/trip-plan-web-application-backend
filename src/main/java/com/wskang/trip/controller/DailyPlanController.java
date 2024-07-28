package com.wskang.trip.controller;

import com.wskang.trip.dto.DailyPlanDto;
import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.service.DailyPlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/daily-plans")
@AllArgsConstructor
public class DailyPlanController {

    private DailyPlanService dailyPlanService;

    // dailyPlan을 add 하는 REST API
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')") // SecurityContext 에 해당하는 ROLE 을 소유하고 있을 때만 메서드 호출 허용 (그렇지 않으면 HTTP 403 Forbidden 반환)
    public ResponseEntity<DailyPlanDto> addDailyPlan(@RequestBody DailyPlanDto dailyPlanDto){
        DailyPlanDto savedDailyPlan = dailyPlanService.addDailyPlan(dailyPlanDto);
        return new ResponseEntity<>(savedDailyPlan, HttpStatus.CREATED);
    }

    // 특정 dailyId를 갖는 dailyPlan 1개를 return 하는 REST API
    @GetMapping("{dailyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DailyPlanDto> getDailyPlan(@PathVariable("dailyId") Long dailyId){
        DailyPlanDto dailyPlanDto = dailyPlanService.getDailyPlan(dailyId);
        return ResponseEntity.ok(dailyPlanDto);
    }

    // 특정 planId를 갖는 Plan의 모든 dailyPlan을 return 하는 REST API
    @GetMapping("plan/{planId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<DailyPlanDto>> getAllDailyPlans(@PathVariable("planId") Long planId){
        List<DailyPlanDto> dailyPlans = dailyPlanService.getAllDailyPlans(planId);
        return ResponseEntity.ok(dailyPlans);
    }

    // dailyPlan을 update 하는 REST API
    @PutMapping("{dailyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DailyPlanDto> updateDailyPlan(@RequestBody DailyPlanDto dailyPlanDto, @PathVariable("dailyId") Long dailyId){
        DailyPlanDto updatedDailyPlan = dailyPlanService.updateDailyPlan(dailyPlanDto, dailyId);
        return ResponseEntity.ok(updatedDailyPlan);
    }

    // dailyPlan을 delete 하는 REST API
    @DeleteMapping("{dailyId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> deletePlan(@PathVariable("dailyId") Long dailyId){
        dailyPlanService.deleteDailyPlan(dailyId);
        return ResponseEntity.ok("Daily Plan deleted Successfully!");
    }
}
