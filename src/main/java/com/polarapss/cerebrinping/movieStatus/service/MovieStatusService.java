package com.polarapss.cerebrinping.movieStatus.service;

import com.polarapss.cerebrinping.movieStatus.dto.MovieStatusDTO;
import com.polarapss.cerebrinping.movieStatus.model.entity.MovieStatus;
import com.polarapss.cerebrinping.movieStatus.model.repository.MovieStatusRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class MovieStatusService {


    private final MovieStatusRepository movieStatusRepository;

    public ResponseEntity<List<MovieStatusDTO>> getAllMovieStatus() {
        List<MovieStatus> movieStatuses = movieStatusRepository.findAll();
        List<MovieStatusDTO> movieStatusDTOs = new ArrayList<>();
        for (MovieStatus movieStatus : movieStatuses) {
            movieStatusDTOs.add(new MovieStatusDTO(movieStatus));
        }
        return new ResponseEntity<>(movieStatusDTOs, HttpStatus.OK);
    }

    public ResponseEntity<MovieStatusDTO> getMovieStatusById(Long movieId) {
        Optional<MovieStatus> movieStatus = movieStatusRepository.findById(movieId);
        return movieStatus.map(status -> new ResponseEntity<>(new MovieStatusDTO(status), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<MovieStatusDTO> addMovieStatus(MovieStatusDTO movieStatusDTO) {
        MovieStatus movieStatus = new MovieStatus();
        movieStatus.setStatus(movieStatusDTO.getStatus());
        movieStatusRepository.save(movieStatus);
        return new ResponseEntity<>(new MovieStatusDTO(movieStatus), HttpStatus.OK);
    }

    public ResponseEntity<MovieStatusDTO> updateMovieStatus(Long movieId, MovieStatusDTO movieStatusDTO) {
        Optional<MovieStatus> movieStatus = movieStatusRepository.findById(movieId);
        if (movieStatus.isPresent()) {
            movieStatus.get().setStatus(movieStatusDTO.getStatus());
            movieStatusRepository.save(movieStatus.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieStatusDTO.setId(movieId);

        return new ResponseEntity<>(movieStatusDTO, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteMovieStatus(Long movieId) {
        Optional<MovieStatus> movieStatus = movieStatusRepository.findById(movieId);
        if (movieStatus.isPresent()) {
            movieStatusRepository.delete(movieStatus.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}