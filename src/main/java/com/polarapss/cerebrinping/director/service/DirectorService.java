package com.polarapss.cerebrinping.director.service;

import com.polarapss.cerebrinping.director.dto.DirectorDTO;
import com.polarapss.cerebrinping.director.model.entity.Director;
import com.polarapss.cerebrinping.director.model.repository.DirectorRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        List<DirectorDTO> directorDTOs = new ArrayList<>();
        directorRepository.findAll().forEach(director -> {
            directorDTOs.add(new DirectorDTO(director));
        });

        return new ResponseEntity<>(directorDTOs, HttpStatus.OK);
    }

    public ResponseEntity<DirectorDTO> createDirector(DirectorDTO directorDTO) {
        Director director = new Director();
        director.setName(directorDTO.getName());
        director = directorRepository.save(director);

        directorDTO.setId(director.getId());
        return new ResponseEntity<>(directorDTO, HttpStatus.CREATED);
    }
}
