package com.uvt.project.uvt_prime_video.movie;


import com.uvt.project.uvt_prime_video.exception.MovieConflictException;
import com.uvt.project.uvt_prime_video.exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getAllByName(String name){
        return movieRepository.findAllByNameContainingIgnoreCase(name);
    }

    public Movie getMovieById(Integer movieId){
        String errorMessage = String.format("Filmul cu id %d nu exista!", movieId);
        return movieRepository.findById(movieId).
                orElseThrow(() -> new MovieNotFoundException(errorMessage));
    }


    public void addMovie(Movie newMovie){

        //verificare daca filmul nu exista cumva deja in baza de date
        Optional<Movie> movieOptional = movieRepository.findByName(newMovie.getName());
        if(movieOptional.isPresent()){
            throw new MovieConflictException("Filmul exista deja in lista de filme");
        }

        movieRepository.save(newMovie);
    }

    public void updateMovie(Integer movieId, Movie newMovie){

        //verificare daca filmul la care se face update exista in lista de filme, ar trebui sa existe
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if(movieOptional.isEmpty()){
            throw new MovieNotFoundException("Filmul nu exista in lista de filme!");
        }

        //verificare ca dupa schimbare numelui sa nu fie un conflict intre filme
        if(movieRepository.findByName(newMovie.getName()).isPresent()){
            throw new MovieConflictException("Filmul cu acest nume exista deja in lista de filme");
        }

        //Facem update la toate atributele filmului
        Movie movieToUpdate = movieOptional.get();
        movieToUpdate.setName(newMovie.getName());
        movieToUpdate.setImageUrl(newMovie.getImageUrl());
        movieToUpdate.setDirectorName(newMovie.getDirectorName());
        movieToUpdate.setRentingCost(newMovie.getRentingCost());
        movieToUpdate.setCategory(newMovie.getCategory());
        movieToUpdate.setReleaseDate(newMovie.getReleaseDate());
        movieToUpdate.setDurationInMinutes(newMovie.getDurationInMinutes());

        //movieToUpdate este filmul deja existent in baza de date care isi pastreaza id-ul dar is schimba atributele
        movieRepository.save(movieToUpdate);
    }

    public void deleteMovie(Integer movieId){

        String errorMessage = String.format("Filmul cu id %d nu exista!", movieId);
        Movie movieToDelete = movieRepository.findById(movieId)
                .orElseThrow(() -> new MovieNotFoundException(errorMessage));

        movieRepository.delete(movieToDelete);
    }
}
