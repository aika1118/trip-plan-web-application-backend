package com.wskang.trip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * DailyPlan
 *
 * DailyPlan 관련된 Entity 정의
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DailyPlans")
public class DailyPlan {

    // 기본키 정의
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_id")
    private Long dailyId;

    @Column(name = "daily_name", nullable = false)
    private String dailyName;

    // DailyPlan -> Plan | plan_id : 외래키
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    // SubPlan -> DailyPlan | daily_id : 외래키
    @OneToMany(mappedBy = "dailyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SubPlan> subPlans;
}

