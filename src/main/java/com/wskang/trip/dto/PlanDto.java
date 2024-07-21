package com.wskang.trip.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
    private Long plan_id;
    private String plan_name;
    private Long user_id; // 외래키
}

