package com.wskang.trip.controller;

import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/plans")
@AllArgsConstructor
public class PlanController {

    private PlanService planService;

    // Build Add REST API
    @PostMapping
    public ResponseEntity<PlanDto> addPlan(@RequestBody PlanDto planDto){
        PlanDto savedPlan = planService.addPlan(planDto);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    // Build Get REST API
    @GetMapping("{id}")
    public ResponseEntity<PlanDto> getPlan(@PathVariable("id") Long planId){
        PlanDto planDto = planService.getPlan(planId);
        return ResponseEntity.ok(planDto);
    }

    // Build Get All REST API
    @GetMapping
    public ResponseEntity<List<PlanDto>> getAllPlans(){
        List<PlanDto> plans = planService.getAllPlans();
        return ResponseEntity.ok(plans);
    }

    // Build Update REST API
    @PutMapping("{id}")
    public ResponseEntity<PlanDto> updatePlan(@RequestBody PlanDto planDto, @PathVariable("id") Long planId){
        PlanDto updatedPlan = planService.updatePlan(planDto, planId);
        return ResponseEntity.ok(updatedPlan);
    }

    // Build Delete REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePlan(@PathVariable("id") Long planId){
        planService.deletePlan(planId);
        return ResponseEntity.ok("Plan deleted Successfully!");
    }

}
