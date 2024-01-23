package com.uvt.project.uvt_prime_video.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ApiException {
    private HttpStatus statusCode;
    private String message;
    private final LocalDateTime timestamp;
}
