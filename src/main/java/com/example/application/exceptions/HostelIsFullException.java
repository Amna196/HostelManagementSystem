package com.example.application.exceptions;

/**
 * @author Amna
 * @created 7/14/2022
 */
public class HostelIsFullException extends GenericException{
    public HostelIsFullException(String errorMessage) {
        super(errorMessage);
    }
}