package com.wskang.trip.controller;

import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PlanController
 *
 * Plan 관련된 REST API 요청을 처리하는 Controller
 *
 */

@CrossOrigin("*")
@RestController
@RequestMapping("api/plans")
@AllArgsConstructor
public class PlanController {

    private PlanService planService;

    // Build Add REST API
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PlanDto> addPlan(@RequestBody PlanDto planDto){
        PlanDto savedPlanDto = planService.addPlan(planDto);
        return new ResponseEntity<>(savedPlanDto, HttpStatus.CREATED);
    }

    // Build Get REST API
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PlanDto> getPlan(@PathVariable("id") Long planId){
        PlanDto planDto = planService.getPlan(planId);
        return ResponseEntity.ok(planDto);
    }

    // Build Get All REST API
    @GetMapping("user/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<PlanDto>> getAllPlans(@PathVariable("userId") Long userId){
        List<PlanDto> plans = planService.getAllPlans(userId);
        return ResponseEntity.ok(plans);
    }

    // Build Update REST API
    @PutMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<PlanDto> updatePlan(@RequestBody PlanDto planDto, @PathVariable("id") Long planId){
        PlanDto updatedPlan = planService.updatePlan(planDto, planId);
        return ResponseEntity.ok(updatedPlan);
    }

    // Build Delete REST API
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<String> deletePlan(@PathVariable("id") Long planId){
        planService.deletePlan(planId);
        return ResponseEntity.ok("Plan deleted Successfully!");
    }

}
