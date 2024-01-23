package com.uvt.project.uvt_prime_video.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MovieController {

    private final MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movieList = movieService.getAllMovies();
        if (movieList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movieList);
    }


    //Asta ar putea fi un search bar mai tarziu -> film dupa nume
    @RequestMapping(method = RequestMethod.GET, params = "name")
    public ResponseEntity<List<Movie>> getMoviesByName(@RequestParam("name") String movieName) {
        List<Movie> moviesByName = movieService.getAllByName(movieName);
        if (moviesByName.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(moviesByName);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Integer movieId) {
        Movie movie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movie);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        movieService.addMovie(movie);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(movie.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("id") Integer movieId,
                                             @Valid @RequestBody Movie movie){
        movieService.updateMovie(movieId, movie);
        Movie updatedMovie = movieService.getMovieById(movieId);
        return ResponseEntity.ok(updatedMovie);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") Integer movieId) {
        Movie deletedMovie = movieService.getMovieById(movieId);
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok(deletedMovie);
    }

}
