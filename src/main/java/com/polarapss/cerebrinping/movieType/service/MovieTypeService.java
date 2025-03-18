package com.polarapss.cerebrinping.movieType.service;

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
import java.util.Optional;

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

    public ResponseEntity<Void> deleteMovieType(Long movieTypeId) {
        if(movieTypeRepository.existsById(movieTypeId)){
            movieTypeRepository.deleteById(movieTypeId);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<MovieTypeDTO> updateMovieType(Long movieTypeId, MovieTypeDTO movieTypeDTO) {
        Optional<MovieType> movieType = movieTypeRepository.findById(movieTypeId);
        if(movieType.isPresent()){
            movieType.get().setType(movieTypeDTO.getType());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        MovieType savedMovieType = movieTypeRepository.save(movieType.get());
        movieTypeDTO.setId(savedMovieType.getId());

        return new ResponseEntity<>(movieTypeDTO, HttpStatus.OK);
    }
}
