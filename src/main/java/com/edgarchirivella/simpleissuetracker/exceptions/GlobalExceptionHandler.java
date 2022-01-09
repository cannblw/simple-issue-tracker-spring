package com.edgarchirivella.simpleissuetracker.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApplicationError> handleEntityNotFoundException(EntityNotFoundException ex) {
        return CreateResponseError(ErrorMessages.RESOURCE_NOT_FOUND, NOT_FOUND);
    }

    @ExceptionHandler(TeamCapacityExceededException.class)
    public ResponseEntity<ApplicationError> handleTeamCapacityExceededException(TeamCapacityExceededException ex) {
        return CreateResponseError(ErrorMessages.TEAM_CAPACITY_EXCEEDED, BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApplicationError> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return CreateResponseError(ErrorMessages.RESOURCE_NOT_FOUND, NOT_FOUND);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApplicationError> handleResponseStatusException(ResponseStatusException ex) {
        return switch (ex.getStatus()) {
            case NOT_FOUND -> CreateResponseError(ErrorMessages.RESOURCE_NOT_FOUND, NOT_FOUND);
            default -> throw ex;
        };
    }

    // See https://www.javaguides.net/2021/04/spring-boot-dto-validation-example.html
    // Used to send validation error messages to the client
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        var errors = new HashMap<String, String>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApplicationError> CreateResponseError(String message, HttpStatus status) {
        return new ResponseEntity<>(new ApplicationError(message, status.value()), status);
    }
}
