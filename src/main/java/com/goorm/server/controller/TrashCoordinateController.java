package com.goorm.server.controller;

import com.goorm.server.domain.Beach;
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
        TotalTrashResponse response = trashCoordinateService.getTotalTrashCount(beach);
        return Response.ofSuccess("OK", response);
    }
}
