package com.polarapss.cerebrinping.movieType.dto;

import com.polarapss.cerebrinping.movieType.model.entity.MovieType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieTypeDTO {
    private Long id;
    private String type;

    public MovieTypeDTO(MovieType movieType) {
        this.id = movieType.getId();
        this.type = movieType.getType();
    }

}
