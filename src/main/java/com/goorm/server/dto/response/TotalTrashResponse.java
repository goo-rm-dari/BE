package com.goorm.server.dto.response;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class TotalTrashResponse {
    private int totalTrashCount;
}
