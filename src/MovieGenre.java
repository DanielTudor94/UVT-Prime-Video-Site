package com.uvt.project.uvt_prime_video.movie;

public enum MovieGenre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    FANTASY("Fantasy"),
    HORROR("Horror"),
    SCIENCE_FICTION("SF"),
    THRILLER("Thriller");

    private final String name;

    MovieGenre(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
