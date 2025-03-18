package com.polarapss.cerebrinping.movie.controller;

import com.polarapss.cerebrinping.director.dto.DirectorDTO;
import com.polarapss.cerebrinping.movie.dto.CreateMovieDTO;
import com.polarapss.cerebrinping.movie.dto.MovieDTO;
import com.polarapss.cerebrinping.movie.model.entity.Movie;
import com.polarapss.cerebrinping.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies(){
        return movieService.getAllMovies();
    }

    @PostMapping
    public ResponseEntity<MovieDTO> createMovie(@RequestBody CreateMovieDTO requestDTO) {
        return movieService.createMovie(requestDTO);
    }

    @PatchMapping("/{movieId}/streaming_platforms/{streamingId}")
    public ResponseEntity<MovieDTO> assingStreamToMovie(@PathVariable Long movieId, @PathVariable Long streamingId) {
        return movieService.assingPLatformToMovie(movieId, streamingId);
    }

    @PatchMapping("/{movieId}/streaming_platforms")
    public ResponseEntity<MovieDTO> assingMultiplePLatformsToMovie(@PathVariable Long movieId, @RequestBody List<Long> streamingIds) {
        return movieService.assingMultiplePLatformsToMovie(movieId, streamingIds);
    }

    @PatchMapping("/{movieId}/directors/{directorId}")
    public ResponseEntity<MovieDTO> assingDirectorToMovie(@PathVariable Long movieId, @PathVariable Long directorId) {
        return movieService.assingDirectorToMovie(movieId, directorId);
    }

    @PatchMapping("/{movieId}/directors")
    public ResponseEntity<MovieDTO> assingDirectorsToMovie(@PathVariable Long movieId, @RequestBody List<Long> directorIds) {
        return movieService.assingMultipleDirectorToMovie(movieId, directorIds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        //For the moment will be void in a future maybe will return the DTO
        return movieService.deleteMovie(id);
    }
}
