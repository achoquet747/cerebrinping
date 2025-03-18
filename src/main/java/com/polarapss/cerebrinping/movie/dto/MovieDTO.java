package com.polarapss.cerebrinping.movie.dto;

import com.polarapss.cerebrinping.director.dto.DirectorDTO;
import com.polarapss.cerebrinping.director.model.entity.Director;
import com.polarapss.cerebrinping.movie.model.entity.Movie;
import com.polarapss.cerebrinping.movieStatus.dto.MovieStatusDTO;
import com.polarapss.cerebrinping.movieType.dto.MovieTypeDTO;
import com.polarapss.cerebrinping.streamingPlatform.model.entity.StreamingPlatform;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MovieDTO {
    private Long id;
    private String title;
    private Year year;
    private MovieStatusDTO movieStatus;
    private MovieTypeDTO movieType;
    private List<StreamingPlatform> streamingPlatforms;
    private List<DirectorDTO> directors;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.year = movie.getYear();

        if(movie.getMovieStatus() != null) {
            this.movieStatus = new MovieStatusDTO(movie.getMovieStatus());
        }
        if(movie.getMovieType() != null) {
            this.movieType = new MovieTypeDTO(movie.getMovieType());
        }
        this.streamingPlatforms = new ArrayList<>();
        if(movie.getStreamingPlatforms() != null) {
            streamingPlatforms.addAll(movie.getStreamingPlatforms());
        }
        this.directors = new ArrayList<>();
        if(movie.getDirectors() != null) {
            directors.addAll(convertListToListDTO(movie.getDirectors()));
        }

    }

    private List<DirectorDTO> convertListToListDTO(List<Director> directors) {
        List<DirectorDTO> list = new ArrayList<>();
        for (Director director : directors) {
            list.add(new DirectorDTO(director));
        }
        return list;
    }
}
