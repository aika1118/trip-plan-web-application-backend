package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * SubPlanDto
 *
 * SubPlan 관련된 DTO 정의
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubPlanDto {

    private Long subId;
    private String type;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;
    private String vehicle;
    private Long money;
    private String currency;
    private String comment;
    private Boolean isComplete;
    private Long dailyId; // 외래키

}