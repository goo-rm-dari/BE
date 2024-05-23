package com.goorm.server.controller;

import com.goorm.server.domain.Beach;
import com.goorm.server.dto.response.BeachInfoResponse;
import com.goorm.server.dto.response.Response;
import com.goorm.server.dto.response.TotalTrashResponse;
import com.goorm.server.service.TrashCoordinateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TrashCoordinate", description = "쓰레기 좌표 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/trash")
public class TrashCoordinateController {

    private final TrashCoordinateService trashCoordinateService;

    @Operation(summary = "해변에서 주운 쓰레기 횟수 조회")
    @GetMapping("/count")
    public Response<?> getTotalTrashCount(
            @RequestParam(required = false, defaultValue = "GWANGCHIGI")
            @Parameter(description = "해변 이름", example = "GWANGCHIGI")
            Beach beach
    ) {
        TotalTrashResponse response = trashCoordinateService.findTotalTrashCount(beach);
        return Response.ofSuccess("OK", response);
    }

    @Operation(summary = "쓰레기가 존재하는 가까운 해변 조회")
    @GetMapping("/nearby-beach")
    public Response<?> getNearbyBeachesWithTrash(
            @RequestParam
            @Parameter(description = "현재 위도", example = "33.450004")
            double lat,
            @RequestParam
            @Parameter(description = "현재 경도", example = "126.918574")
            double lng
    ) {
        BeachInfoResponse beaches = trashCoordinateService.findNearbyBeachesWithTrash(lat, lng);
        return Response.ofSuccess("OK", beaches);
    }
}
