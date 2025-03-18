package com.polarapss.cerebrinping.movie.service;

import com.polarapss.cerebrinping.movie.model.entity.Movie;
import com.polarapss.cerebrinping.movie.model.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

}
