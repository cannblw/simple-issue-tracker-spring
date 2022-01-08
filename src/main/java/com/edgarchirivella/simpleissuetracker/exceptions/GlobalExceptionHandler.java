package com.edgarchirivella.simpleissuetracker.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApplicationError> handleResponseStatusException(EntityNotFoundException ex) {
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

    private ResponseEntity<ApplicationError> CreateResponseError(String message, HttpStatus status) {
        return new ResponseEntity<>(new ApplicationError(message, status.value()), status);
    }
}
