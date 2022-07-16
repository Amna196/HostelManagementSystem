package com.example.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Amna
 * @created 7/13/2022
 */
@AllArgsConstructor
@Getter
public enum BedSize {
    SINGLE("single"),
    DOUBLE("double");
    private final String size;
}
