package com.uvt.project.uvt_prime_video.exception;

public class MovieConflictException extends RuntimeException {
    public MovieConflictException() {
        super("Conflict intre doua filme!");
    }

    public MovieConflictException(String message) {
        super(message);
    }
}
