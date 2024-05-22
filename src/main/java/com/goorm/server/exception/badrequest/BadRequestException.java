package com.goorm.server.exception.badrequest;

import com.goorm.server.exception.GoormException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends GoormException {

    public BadRequestException(String message, int code) {
        super(HttpStatus.BAD_REQUEST, message, code);
    }
}
