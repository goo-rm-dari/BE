package com.goorm.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "moving_coordinate")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MovingCoordinate extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moving_coordinate_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plogging_record_id")
    private PloggingRecord ploggingRecord;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lng", nullable = false)
    private double lng;

    public MovingCoordinate(PloggingRecord ploggingRecord, double lat, double lng) {
        this.ploggingRecord = ploggingRecord;
        this.lat = lat;
        this.lng = lng;
    }
}
