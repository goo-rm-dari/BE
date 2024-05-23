package com.goorm.server.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class CoordinateInfoDTO {

    private double lat;

    private double lng;

    private boolean isTrash;
}
