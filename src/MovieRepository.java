package com.uvt.project.uvt_prime_video.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByName(String name);
    List<Movie> findAllByNameContainingIgnoreCase(String name);
}
