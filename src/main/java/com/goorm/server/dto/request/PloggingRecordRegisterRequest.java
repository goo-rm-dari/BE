
package com.goorm.server.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class PloggingRecordRegisterRequest {

    @NotNull(message = "1005:공백일 수 없습니다.")
    private String memberId;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private List<CoordinateInfoDTO> movingCoordinates;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private List<CoordinateInfoDTO> trashCoordinates;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private int trashCount;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private double totalCalories;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private int movingTime;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private double movingDistance;
}
