package com.wskang.trip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SubPlans")
public class SubPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private Long subId;

    @Column(name = "type")
    private String type;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "money")
    private Long money;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "comment")
    private String comment;

    @Column(name = "is_complete")
    private Boolean isComplete;

    // SubPlan -> DailyPlan | daily_id : 외래키
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "daily_id", nullable = false)
    private DailyPlan dailyPlan;
}

