package com.example.application.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Amna
 * @created 7/13/2022
 */
@AllArgsConstructor
@Getter
public enum HostelName {
    RED("red"),
    PINK("pink"),
    BLUE("blue"),
    BLACK("black");
    private final String name;
}
