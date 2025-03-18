package com.polarapss.cerebrinping.director.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.polarapss.cerebrinping.movie.model.entity.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "director_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 60)
    private String name;

    @JsonIgnore // we will ignore this field for the JSON  is for avoid the infinite recursive
    @ManyToMany(mappedBy = "directors")
    private List<Movie> movies;

}
