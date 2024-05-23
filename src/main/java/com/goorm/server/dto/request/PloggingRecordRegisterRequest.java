
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
    private List<CoordinateInfoDTO> coordinateInfos;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private int count;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private double totalCalories;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private int movingTime;

    @NotNull(message = "1005:공백일 수 없습니다.")
    private double movingDistance;
}
