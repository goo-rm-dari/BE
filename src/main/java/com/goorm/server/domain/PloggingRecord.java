package com.goorm.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plogging_record")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PloggingRecord extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plogging_record_id")
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @OneToMany(mappedBy = "ploggingRecord", fetch = FetchType.LAZY)
    private List<CoordinateInfo> coordinateInfos = new ArrayList<>();

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "total_calories", nullable = false)
    private double totalCalories;

    @Column(name = "moving_time", nullable = false)
    private int movingTime;

    @Column(name = "moving_distance", nullable = false)
    private double movingDistance;

    public PloggingRecord(Long memberId, List<CoordinateInfo> coordinateInfos, int count, double totalCalories, int movingTime, double movingDistance) {
        this.memberId = memberId;
        this.coordinateInfos = coordinateInfos;
        this.count = count;
        this.totalCalories = totalCalories;
        this.movingTime = movingTime;
        this.movingDistance = movingDistance;
    }

    public void setCoordinateInfos(List<CoordinateInfo> coordinateInfos) {
        this.coordinateInfos = coordinateInfos;
    }
}
