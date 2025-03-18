package com.polarapss.cerebrinping.movieType.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polarapss.cerebrinping.movie.model.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class MovieType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_type_id")
    private Long id;

    private String type;

    @JsonIgnore
    @OneToMany(mappedBy = "movieType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> movies;
}
