package com.uvt.project.uvt_prime_video.utils;

import com.uvt.project.uvt_prime_video.movie.MovieGenre;

import static com.uvt.project.uvt_prime_video.movie.MovieGenre.*;
import static com.uvt.project.uvt_prime_video.movie.MovieGenre.THRILLER;

public interface StringUtils {

    static MovieGenre stringToEnum(String stringGenre){
        return switch (stringGenre.toUpperCase()){
            case "ACTION"->  ACTION;
            case "COMEDY" -> COMEDY;
            case "ADVENTURE" -> ADVENTURE;
            case "DRAMA" -> DRAMA;
            case "FANTASY" -> FANTASY;
            case "HORROR" -> HORROR;
            case "SF" -> SCIENCE_FICTION;
            case "THRILLER" -> THRILLER;
            default -> null;
        };
    }

}
