package com.goorm.server.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.security.InvalidParameterException;

@RequiredArgsConstructor
@Getter
public enum Beach {

    GWANGCHIGI("GWANGCHIGI"),
    HYEOPJAE("HYEOPJAE"),
    HAMDEOK("HAMDEOK"),
    DEFAULT("DEFAULT");

    private final String beachName;

    public static Beach fromString(String beachName) {
        return switch (beachName.toUpperCase()) {
            case "GWANGCHIGI" -> GWANGCHIGI;
            case "HYEOPJAE" -> HYEOPJAE;
            case "HAMDEOK" -> HAMDEOK;
            default -> throw new InvalidParameterException();
        };
    }
}
