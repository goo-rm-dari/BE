package com.goorm.server.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class KakaoLoginRequest {

    @NotBlank(message = "1005:공백일 수 없습니다.")
    private String token;
}
