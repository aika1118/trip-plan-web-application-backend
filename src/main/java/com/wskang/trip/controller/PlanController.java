package com.wskang.trip.controller;

import com.wskang.trip.dto.PlanDto;
import com.wskang.trip.service.PlanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PlanDto> getTodo(@PathVariable("id") Long planId){
        PlanDto planDto = planService.getPlan(planId);
        return ResponseEntity.ok(planDto);
    }
}
