package com.example.application.exception;

/**
 * @author Amna
 * @created 7/14/2022
 */
public class RoomIsFullException extends GenericException{
    public RoomIsFullException(String errorMessage) {
        super(errorMessage);
    }
}