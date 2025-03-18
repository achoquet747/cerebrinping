package com.polarapss.cerebrinping.director.controller;

import com.polarapss.cerebrinping.director.dto.DirectorDTO;
import com.polarapss.cerebrinping.director.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movies/directors")
@RequiredArgsConstructor
public class DirectorController {

    private final DirectorService directorService;


    @GetMapping
    public ResponseEntity<List<DirectorDTO>> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @PostMapping
    public ResponseEntity<DirectorDTO> createDirector(@RequestBody DirectorDTO director) {
        return directorService.createDirector(director);
    }

}
