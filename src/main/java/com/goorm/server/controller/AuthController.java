package com.goorm.server.controller;

import com.goorm.server.dto.request.AuthLoginRequest;
import com.goorm.server.dto.request.KakaoLoginRequest;
import com.goorm.server.dto.response.OAuthTokenResponse;
import com.goorm.server.dto.response.Response;
import com.goorm.server.dto.response.TokenResponse;
import com.goorm.server.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Login", description = "인증")
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "이메일 로그인")
    @PostMapping
    public Response<?> login(@RequestBody @Valid AuthLoginRequest request) {
        TokenResponse response = authService.login(request);
        return Response.ofSuccess("OK", response);
    }

    @Operation(summary = "카카오 OAuth 로그인")
    @PostMapping("/kakao")
    public Response<?> loginKakao(@RequestBody @Valid KakaoLoginRequest request) {
        OAuthTokenResponse response = authService.kakaoOAuthLogin(request);
        return Response.ofSuccess("OK", response);
    }
}
