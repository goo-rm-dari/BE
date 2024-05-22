package com.goorm.server.exception.unauthorized;

public class InvalidKakaoTokenException extends UnauthorizedException {

    public InvalidKakaoTokenException() {
        super("올바르지 않은 카카오 토큰입니다.", 1001);
    }
}
