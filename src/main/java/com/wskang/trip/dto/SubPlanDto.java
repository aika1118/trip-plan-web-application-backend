package com.wskang.trip.dto;

import com.wskang.trip.entity.DailyPlan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalTime;

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