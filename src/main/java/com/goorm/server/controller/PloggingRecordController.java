package com.goorm.server.controller;

import com.goorm.server.dto.request.PloggingRecordRegisterRequest;
import com.goorm.server.dto.response.PloggingRecordListResponse;
import com.goorm.server.dto.response.PloggingRecordRegisterResponse;
import com.goorm.server.dto.response.PloggingRecordResponse;
import com.goorm.server.dto.response.Response;
import com.goorm.server.service.PloggingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PloggingRecords", description = "플로깅 기록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plogging-records")
public class PloggingRecordController {

    private final PloggingRecordService ploggingRecordService;

    @Operation(summary = "사용자 기록 등록")
    @PostMapping
    public Response<?> registerRecord(@RequestBody @Valid PloggingRecordRegisterRequest request) {
        PloggingRecordRegisterResponse response = ploggingRecordService.registerPloggingRecord(request);
        return Response.ofSuccess("OK", response);
    }

    @Operation(summary = "사용자 전체 기록 조회")
    @GetMapping("/all/{memberId}")
    public Response<?> getPloggingRecords(@PathVariable String memberId) {
        PloggingRecordListResponse response = ploggingRecordService.getPloggingRecords(memberId);
        return Response.ofSuccess("OK", response);
    }

    @Operation(summary = "특정 플로깅 기록 조회")
    @GetMapping("/{recordId}")
    public Response<?> getPloggingRecord(@PathVariable Long recordId) {
        PloggingRecordResponse response = ploggingRecordService.getPloggingRecord(recordId);
        return Response.ofSuccess("OK", response);
    }
}
