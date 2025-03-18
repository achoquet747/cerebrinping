package com.polarapss.cerebrinping.movieStatus.dto;

import com.polarapss.cerebrinping.movieStatus.model.entity.MovieStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MovieStatusDTO {
    private Long id;
    private String status;

    public MovieStatusDTO(MovieStatus movieStatus) {
        this.id = movieStatus.getId();
        this.status = movieStatus.getStatus();
    }
}
