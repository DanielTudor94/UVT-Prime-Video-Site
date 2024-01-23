package com.uvt.project.uvt_prime_video;

import com.uvt.project.uvt_prime_video.movie.Movie;
import com.uvt.project.uvt_prime_video.movie.MovieGenre;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class UvtPrimeVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(UvtPrimeVideoApplication.class, args);
    }

}
