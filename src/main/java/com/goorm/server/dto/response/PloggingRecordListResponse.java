package com.goorm.server.dto.response;

import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class PloggingRecordListResponse {
    private List<PloggingRecordResponse> data;
}
