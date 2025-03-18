package com.polarapss.cerebrinping.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieDTO {

    private Long id;
    private String title;
    private Year year;
    private Long movieStatusId;
    private Long movieTypeId;

}
