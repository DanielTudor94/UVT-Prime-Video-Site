package com.uvt.project.uvt_prime_video.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(){
        super("Filmul nu exista!");
    }
    public MovieNotFoundException(String message) {
        super(message);
    }
}
