package com.homework.employeetracker.exception;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> messages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
            .forEach(error -> messages.add(error.getField() + ": " + error.getDefaultMessage()));
        return buildResponse(HttpStatus.BAD_REQUEST, messages);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, String message) {
        return buildResponse(status, Collections.singletonList(message));
    }

    private ResponseEntity<Object> buildResponse(HttpStatus status, List<String> messages) {
        GenericErrorResponse responseBody = new GenericErrorResponse(
            Timestamp.from(Instant.now()), status.value(), messages);

        return new ResponseEntity<>(responseBody, status);
    }

}
