package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PlanDto
 *
 * Plan 관련된 DTO 정의
 *
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
    private Long planId;
    private String planName;
    private Long userId; // 외래키
}

