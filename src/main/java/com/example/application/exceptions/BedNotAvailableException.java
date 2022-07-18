package com.example.application.exceptions;

/**
 * @author Amna
 * @created 7/14/2022
 */
public class BedNotAvailableException extends GenericException{
    public BedNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}