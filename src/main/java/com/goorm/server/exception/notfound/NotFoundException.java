package com.goorm.server.exception.notfound;

import com.goorm.server.exception.GoormException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends GoormException {

    public NotFoundException(String message, int code) {
        super(HttpStatus.NOT_FOUND, message, code);
    }
}
