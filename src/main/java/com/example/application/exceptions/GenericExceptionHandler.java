package com.example.application.exceptions;

import com.example.application.utils.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

/**
 * @author Amna
 * @created 7/14/2022
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GenericExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionHandler.class);

    private final MessageSource messageSource;
    private static final String FIVE_HUNDRED = "500";
    private static final String ATTRIBUTE_CODE_ERROR = "EAV_ATTRIBUTE_ENTITY_TYPE_ID_ATTRIBUTE_CODE";

    @Autowired
    public GenericExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * @param exception CustomNotFoundException
     * @return ResponseEntity<ErrorDetails>
     */
    @ExceptionHandler(BedNotAvailableException.class)
    public ResponseEntity<ErrorDetails> handleBedNotAvailableException(BedNotAvailableException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getUserMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HostelIsFullException.class)
    public ResponseEntity<ErrorDetails> handleHostelIsFullException(HostelIsFullException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getUserMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HostelNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleHostelNotFoundException(HostelNotFoundException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getUserMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(HostelNotValidException.class)
    public ResponseEntity<ErrorDetails> handleHostelNotValidException(HostelNotValidException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getUserMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RoomIsFullException.class)
    public ResponseEntity<ErrorDetails> handleRoomIsFullException(RoomIsFullException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getUserMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<ErrorDetails> handleDuplicateKeyException(DuplicateKeyException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.FOUND);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> handleNullPointerException(NullPointerException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDetails> handleNoSuchElementException(NoSuchElementException exception, WebRequest request) {
        ErrorDetails errorDetails =
                new ErrorDetails(exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

}
