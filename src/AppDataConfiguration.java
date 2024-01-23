package com.uvt.project.uvt_prime_video.configuration;

import com.uvt.project.uvt_prime_video.movie.Movie;
import com.uvt.project.uvt_prime_video.movie.MovieGenre;
import com.uvt.project.uvt_prime_video.utils.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.uvt.project.uvt_prime_video.movie.MovieGenre.*;

@Configuration
public class AppDataConfiguration {


    @Bean
    public CommandLineRunner commandLineRunner(EntityManagerFactory entityManagerFactory) {

//        Movie avatar = new Movie(
//                "Avatar",
//                "testUrl",
//                "James Cameron",
//                0.5,
//                162,
//                LocalDate.of(2009, 9, 23),
//                List.of(ACTION, MovieGenre.ADVENTURE, MovieGenre.FANTASY));
//
//        Movie aceVentura = new Movie(
//                "Ace Ventura: Pet Detective",
//                "testUrl",
//                "Tom Shadyac",
//                0.3,
//                86,
//                LocalDate.of(1994, 2, 4),
//                List.of(MovieGenre.COMEDY));

        Movie lordOfTheRings = new Movie(
                "The Lord of The Rings: The Fellowship of the Ring",
                "testUrl",
                "Peter Jackson",
                0.5,
                178,
                LocalDate.of(2001, 12, 10),
                List.of(ACTION, MovieGenre.ADVENTURE, MovieGenre.FANTASY));

        List<Movie> movieList = initMovieList();


        return args -> {
            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();

            movieList.forEach(em::persist);

            em.getTransaction().commit();
            em.close();


        };
    }


    private List<Movie> initMovieList() {
        List<Movie> movieList = new ArrayList<>();
        String filePath = "src/main/resources/files/IMDB-FILME.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String movieName = parts[0];
                String imageUrl = parts[1];
                String directorName = parts[2];
                Double rentingCost = Double.valueOf(parts[3]);
                Integer duration = Integer.valueOf(parts[4]);
                LocalDate releaseDate = LocalDate.parse(parts[5]);
                List<MovieGenre> genres = Arrays.stream(parts[6].split(" ")).map(StringUtils::stringToEnum).toList();
                Movie movie = new Movie(movieName, imageUrl, directorName, rentingCost, duration, releaseDate, genres);
                movieList.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieList;
    }

}
