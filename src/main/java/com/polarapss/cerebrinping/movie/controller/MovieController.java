package com.polarapss.cerebrinping.movie.controller;

import com.polarapss.cerebrinping.movie.model.entity.Movie;
import com.polarapss.cerebrinping.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }
}
