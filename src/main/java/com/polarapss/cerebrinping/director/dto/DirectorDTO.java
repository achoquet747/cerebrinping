package com.polarapss.cerebrinping.director.dto;

import com.polarapss.cerebrinping.director.model.entity.Director;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DirectorDTO {

    private Long id;
    private String name;

    public DirectorDTO(Director director) {
        this.id = director.getId();
        this.name = director.getName();
    }
}
