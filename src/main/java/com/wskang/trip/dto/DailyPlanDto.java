package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DailyPlanDto
 *
 * DailyPlan 관련된 DTO 정의
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlanDto {
    private Long DailyId;
    private String dailyName;
    private Long planId; // 외래키
}
