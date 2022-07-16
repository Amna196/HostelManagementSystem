package com.example.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Amna
 * @created 7/12/2022
 */
@AllArgsConstructor
@Getter
public enum Gender {
    FEMALE("female"),
    MALE("male");
    private final String name;
}
