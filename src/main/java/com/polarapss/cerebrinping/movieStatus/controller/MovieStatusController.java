package com.polarapss.cerebrinping.movieStatus.controller;

import com.polarapss.cerebrinping.movieStatus.dto.MovieStatusDTO;
import com.polarapss.cerebrinping.movieStatus.service.MovieStatusService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies/status")
@AllArgsConstructor
public class MovieStatusController {

    private MovieStatusService movieStatusService;

    @GetMapping
    public ResponseEntity<List<MovieStatusDTO>> getAllMoviesStatus(){
        return movieStatusService.getAllMovieStatus();
    }

    @GetMapping("/{movieStatusId}")
    public ResponseEntity<MovieStatusDTO> getMovieStatusById(@PathVariable Long movieStatusId){
        return movieStatusService.getMovieStatusById(movieStatusId);
    }

    @PostMapping
    public ResponseEntity<MovieStatusDTO> createMovieStatus(@RequestBody MovieStatusDTO movieStatusDTO){
        return movieStatusService.addMovieStatus(movieStatusDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieStatusDTO> updateMovieStatus(@PathVariable Long id, @RequestBody MovieStatusDTO movieStatusDTO){
        return movieStatusService.updateMovieStatus(id, movieStatusDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieStatus(@PathVariable Long id){
        return movieStatusService.deleteMovieStatus(id);
    }
}
