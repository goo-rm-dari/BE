package com.goorm.server.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@ToString
public class MemberSignUpRequest {

    @Email(message = "1004:이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "1005:공백일 수 없습니다.")
    private String email;

    @NotBlank(message = "1005:공백일 수 없습니다.")
    private String password;
}
