package com.polarapss.cerebrinping.movie.model.entity;

import com.polarapss.cerebrinping.MovieStatus.model.entity.MovieStatus;
import com.polarapss.cerebrinping.MovieType.model.entity.MovieType;
import com.polarapss.cerebrinping.StreamingPlatform.model.entity.StreamingPlatform;
import com.polarapss.cerebrinping.director.model.entity.Director;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "moivie_id")
    private Long id;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "year")
    private Year year;

    @ManyToMany
    @JoinTable(
            name = "Movie_Director",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    foreignKey = @ForeignKey(name = "FK_Movie_Director_Movie")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "director_id",
                    foreignKey = @ForeignKey(name = "FK_Movie_Director_Director")
            )
    )
    private List<Director> directors;

    @ManyToMany
    @JoinTable(
            name = "Movie_StreamingPlatform",
            joinColumns = @JoinColumn(
                    name = "movie_id",
                    foreignKey = @ForeignKey(name = "FK_Movie_StreamingPlatform_Movie")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "streaming_id",
                    foreignKey = @ForeignKey(name = "FK_Movie_StreamingPlatform_Streaming")
            )
    )
    private List<StreamingPlatform> streamingPlatforms;

    @ManyToOne
    @JoinColumn(
            name = "movie_status_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_MovieStatus_Movie")
    )
    private MovieStatus movieStatus;

    @ManyToOne
    @JoinColumn(
            name = "movie_type_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_MovieType_Movie")
    )
    private MovieType movieType;

    @Column(name = "imageURL")
    private String imageURL;
}
