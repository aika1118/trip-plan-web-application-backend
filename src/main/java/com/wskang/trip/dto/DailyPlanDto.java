package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlanDto {
    private Long DailyId;
    private String dailyName;
    private Long planId; // 외래키
}
