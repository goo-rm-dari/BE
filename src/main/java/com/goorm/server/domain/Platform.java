package com.goorm.server.domain;

import com.goorm.server.exception.badrequest.InvalidPlatformException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Platform {

    KAKAO("kakao"),
    GOORM("goorm");

    private String value;

    public static Platform from(String value) {
        return Arrays.stream(values())
                .filter(it -> Objects.equals(it.value, value))
                .findFirst()
                .orElseThrow(InvalidPlatformException::new);
    }
}
