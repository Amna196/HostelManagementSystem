package com.example.application.exceptions;

/**
 * @author Amna
 * @created 7/13/2022
 */
public class HostelNotFoundException extends GenericException {
    public HostelNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
