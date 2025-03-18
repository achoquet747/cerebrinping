package com.polarapss.cerebrinping.movieType.model.service;

import com.polarapss.cerebrinping.movieType.dto.MovieTypeDTO;
import com.polarapss.cerebrinping.movieType.model.entity.MovieType;
import com.polarapss.cerebrinping.movieType.model.repository.MovieTypeRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@AllArgsConstructor
@Service
public class MovieTypeService {

    private final MovieTypeRepository movieTypeRepository;

    public ResponseEntity<List<MovieType>> getAllMovieTypes(){
        return new ResponseEntity<>(movieTypeRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<MovieType> getMovieType(Long movieTypeId){
        MovieType movieType = movieTypeRepository.findById(movieTypeId).
                orElseThrow(() -> new RuntimeException("Movie Type Not Found"));
        return new ResponseEntity<>(movieType, HttpStatus.OK);
    }

    public ResponseEntity<MovieTypeDTO> createMovieType(MovieTypeDTO movieTypeDto) {
        MovieType movieType = MovieType.builder()
                .type(movieTypeDto.getType()).build();

        MovieType savedMovieType = movieTypeRepository.save(movieType);
        movieTypeDto.setId(savedMovieType.getId());

        return new ResponseEntity<>(movieTypeDto, HttpStatus.CREATED);
    }
}
