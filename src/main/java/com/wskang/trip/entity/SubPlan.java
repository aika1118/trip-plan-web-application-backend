package com.wskang.trip.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    private Long sub_id;

    @Column(name = "type")
    private String type;

    @Column(name = "start_time")
    private Timestamp start_time;

    @Column(name = "end_time")
    private Timestamp end_time;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "vehicle")
    private String vehicle;

    @Column(name = "money")
    private Long money;

    @Column(name = "comment")
    private String comment;

    @Column(name = "link")
    private String link;

    @Column(name = "is_complete")
    private Boolean is_complete;

    // SubPlan -> DailyPlan | daily_id : 외래키
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "daily_id", nullable = false)
    private DailyPlan dailyPlan;
}

