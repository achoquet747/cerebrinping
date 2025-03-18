package com.polarapss.cerebrinping.streamingPlatform.model.entity;

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
public class StreamingPlatform {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "streaming_id")
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "streamingPlatforms")
    private List<Movie> movies;
}
