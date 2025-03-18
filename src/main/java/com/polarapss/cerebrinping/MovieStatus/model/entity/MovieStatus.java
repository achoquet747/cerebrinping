package com.polarapss.cerebrinping.MovieStatus.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polarapss.cerebrinping.movie.model.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovieStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_status_id")
    private Long id;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "movieStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Movie> movies;
}
