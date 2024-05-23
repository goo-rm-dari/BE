package com.goorm.server.controller;

import com.goorm.server.dto.request.PloggingRecordRegisterRequest;
import com.goorm.server.dto.response.PloggingRecordRegisterResponse;
import com.goorm.server.dto.response.Response;
import com.goorm.server.service.PloggingRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Records", description = "기록")
@RestController
@RequiredArgsConstructor
@RequestMapping("/plogging-records")
public class PloggingRecordController {

    private final PloggingRecordService ploggingRecordService;

    @Operation(summary = "사용자 기록 등록")
    @PostMapping
    public Response<?> registerRecord(@RequestBody @Valid PloggingRecordRegisterRequest request) {
        PloggingRecordRegisterResponse response = ploggingRecordService.registerPloggingRecord(request);
        return Response.ofSuccess("OK", response);
    }
}
