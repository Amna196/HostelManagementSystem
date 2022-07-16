package com.example.application.exception;

/**
 * @author Amna
 * @created 7/14/2022
 */
public class HostelIsFullException extends GenericException{
    public HostelIsFullException(String errorMessage) {
        super(errorMessage);
    }
}