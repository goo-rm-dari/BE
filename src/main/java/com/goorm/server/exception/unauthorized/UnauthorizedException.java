package com.goorm.server.exception.unauthorized;

import com.goorm.server.exception.GoormException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends GoormException {

    public UnauthorizedException(String message, int code) {
        super(HttpStatus.UNAUTHORIZED, message, code);
    }
}
