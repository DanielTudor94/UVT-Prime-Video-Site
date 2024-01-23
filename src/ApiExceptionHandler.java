package com.uvt.project.uvt_prime_video.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MovieNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNotFound(Exception exception) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException exceptionBody = new ApiException(
                notFound,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionBody, notFound);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        String errorMessage = requireNonNull(exception.getFieldError()).getDefaultMessage();

        ApiException exceptionBody = new ApiException(
                badRequest,
                errorMessage,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionBody, badRequest);
    }

    @ExceptionHandler(value = {MovieConflictException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ResponseEntity<Object> handleConflictException(Exception exception) {
        HttpStatus conflict = HttpStatus.CONFLICT;

        ApiException exceptionBody = new ApiException(
                conflict,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(exceptionBody, conflict);
    }
}
