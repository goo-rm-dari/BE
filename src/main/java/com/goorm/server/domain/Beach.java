package com.goorm.server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;

@RequiredArgsConstructor
@Getter
public enum Beach {

    GWANGCHIGI("gwangchigi"),
    HYEOPJAE("hyeopjae"),
    HAMDEOK("hamdeok"),
    DEFAULT("default");

    private final String beachName;

    public static Beach fromString(String beachName) {
        return switch (beachName.toUpperCase()) {
            case "gwangchigi" -> GWANGCHIGI;
            case "hyeopjae" -> HYEOPJAE;
            case "hamdeok" -> HAMDEOK;
            default -> throw new InvalidParameterException();
        };
    }
}
