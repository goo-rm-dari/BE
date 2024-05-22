package com.goorm.server.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class OAuthMemberSignUpRequest {

    private String email;

    @NotBlank(message = "1005:공백일 수 없습니다.")
    private String platform;

    @NotBlank(message = "1005:공백일 수 없습니다.")
    private String platformId;

    @NotBlank(message = "1005:공백일 수 없습니다.")
    private String nation;
}
