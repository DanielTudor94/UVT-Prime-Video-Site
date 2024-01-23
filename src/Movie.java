package com.uvt.project.uvt_prime_video.movie;

import com.uvt.project.uvt_prime_video.utils.StringUtils;
import com.uvt.project.uvt_prime_video.validation.ValidMovieName;
import com.uvt.project.uvt_prime_video.validation.ValidPersonName;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Movie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @ValidMovieName
    private String name;

    @NotNull
    private String imageUrl;

    @ValidPersonName
    private String directorName;

    @NotNull(message = "Cost de inchiriere inexistent!")
    private Double rentingCost;

    @NotNull(message = "Duratia inexistenta!")

    @Min(value = 5, message = "Filmul trebuie sa fie mai lung de 10 minute")
    private Integer durationInMinutes;

    @NotNull(message = "Data inexistenta!")
    @Past(message = "Data filmului trebuie sa fie in trecut!")
    private LocalDate releaseDate;

    private String category;

    public Movie(String name, String imageUrl, String directorName, Double rentingCost, int durationInMinutes, LocalDate releaseDate, List<MovieGenre> genres) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.rentingCost = rentingCost;
        this.directorName = directorName;
        this.durationInMinutes = durationInMinutes;
        this.releaseDate = releaseDate;
        this.category = categorize(genres);
    }
    private String categorize(List<MovieGenre> genres) {
        return genres.stream()
                .map(MovieGenre::getName)
                .collect(Collectors.joining(", "));
    }
}
