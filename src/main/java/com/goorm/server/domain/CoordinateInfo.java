package com.goorm.server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "coordinate_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CoordinateInfo extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coordinate_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plogging_record_id")
    private PloggingRecord ploggingRecord;

    @Column(name = "lat", nullable = false)
    private double lat;

    @Column(name = "lng", nullable = false)
    private double lng;

    @Column(name = "is_trash", nullable = false)
    private boolean isTrash;

    public CoordinateInfo(PloggingRecord ploggingRecord, double lat, double lng, boolean isTrash) {
        this.ploggingRecord = ploggingRecord;
        this.lat = lat;
        this.lng = lng;
        this.isTrash = isTrash;
    }
}
