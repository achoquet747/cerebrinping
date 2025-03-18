package com.polarapss.cerebrinping.movieType.controller;

import com.polarapss.cerebrinping.movieType.dto.MovieTypeDTO;
import com.polarapss.cerebrinping.movieType.model.entity.MovieType;
import com.polarapss.cerebrinping.movieType.model.service.MovieTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/movies/types")
public class MovieTypeController {

    private MovieTypeService movieTypeService;

    @PostMapping
    public ResponseEntity<MovieTypeDTO> createMovieType(@RequestBody MovieTypeDTO movieTypeDTO){
        return movieTypeService.createMovieType(movieTypeDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieType>> getAllMovieTypes(){
        return movieTypeService.getAllMovieTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieType> getMovieTypeById(@PathVariable Long id){
        return movieTypeService.getMovieType(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovieType(@PathVariable Long id){
        return movieTypeService.deleteMovieType(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovieTypeDTO> updateMovieType(@PathVariable Long id, @RequestBody MovieTypeDTO movieTypeDTO){
        return movieTypeService.updateMovieType(id, movieTypeDTO);
    }
}
