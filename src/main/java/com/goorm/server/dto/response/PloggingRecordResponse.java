package com.goorm.server.dto.response;

import com.goorm.server.dto.request.CoordinateInfoDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class PloggingRecordResponse {
    private LocalDate createdTime;
    private List<CoordinateInfoDTO> movingCoordinates;
    private List<CoordinateInfoDTO> trashCoordinates;
    private int trashCount;
    private double totalCalorie;
    private int movingTime;
    private double movingDistance;
}
