package com.polarapss.cerebrinping.movie.service;

import com.polarapss.cerebrinping.director.dto.DirectorDTO;
import com.polarapss.cerebrinping.director.model.entity.Director;
import com.polarapss.cerebrinping.director.model.repository.DirectorRepository;
import com.polarapss.cerebrinping.errorHandling.NotFoundException;
import com.polarapss.cerebrinping.movie.dto.CreateMovieDTO;
import com.polarapss.cerebrinping.movie.dto.MovieDTO;
import com.polarapss.cerebrinping.movie.model.entity.Movie;
import com.polarapss.cerebrinping.movie.model.repository.MovieRepository;
import com.polarapss.cerebrinping.movieStatus.model.repository.MovieStatusRepository;
import com.polarapss.cerebrinping.movieType.model.repository.MovieTypeRepository;
import com.polarapss.cerebrinping.streamingPlatform.model.entity.StreamingPlatform;
import com.polarapss.cerebrinping.streamingPlatform.model.repository.StreamingPlatformRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieStatusRepository movieStatusRepository;
    private final MovieTypeRepository movieTypeRepository;
    private final StreamingPlatformRepository streamingPlatformRepository;
    private final DirectorRepository directorRepository;

    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> movieDTOs = new ArrayList<>();
        for (Movie movie : movies) {
            movieDTOs.add(new MovieDTO(movie));
        }
        return new ResponseEntity<>(movieDTOs, HttpStatus.OK);
    }

    public ResponseEntity<MovieDTO> getMovieById(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        //In this case with optional if the movie contains value will do map, if is empty will do orElseGet
        return movie.map(value -> new ResponseEntity<>(new MovieDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<MovieDTO> createMovie(CreateMovieDTO requestDTO) {
        Movie movie = new Movie();
        movie.setTitle(requestDTO.getTitle());
        movie.setYear(requestDTO.getYear());

        if(requestDTO.getMovieTypeId() != null) {
            movie.setMovieType(movieTypeRepository.findById(requestDTO.getMovieTypeId())
                    .orElseThrow(() -> new RuntimeException("Movie Type Not Found")));
        }

        if(requestDTO.getMovieStatusId() != null) {
            movie.setMovieStatus(movieStatusRepository.findById(requestDTO.getMovieStatusId())
                    .orElseThrow(() -> new RuntimeException("Movie Status Not Found")));
        }

        Movie savedMovie = movieRepository.save(movie);

        return new ResponseEntity<>(new MovieDTO(savedMovie), HttpStatus.CREATED);
    }

    public ResponseEntity<MovieDTO> assingPLatformToMovie(Long movieId, Long streamingPlatformId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie Not Found"));
        StreamingPlatform streamingPlatform = streamingPlatformRepository.findById(streamingPlatformId)
                .orElseThrow(() -> new RuntimeException("Stream Platform Not Found"));
        movie.getStreamingPlatforms().add(streamingPlatform);

        movieRepository.save(movie);

        return new ResponseEntity<>(new MovieDTO(movie), HttpStatus.CREATED);
    }

    public ResponseEntity<MovieDTO> assingMultiplePLatformsToMovie(Long movieId, List<Long> streamingPlatformIds) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie Not Found"));
        //validate if we have the stream
        if(movie.getStreamingPlatforms().size() == streamingPlatformIds.size() && movie.getStreamingPlatforms().stream()
                .allMatch(streamingPlatform -> streamingPlatformIds.contains(streamingPlatform.getId()))) {
            return new ResponseEntity<>(new MovieDTO(movie), HttpStatus.OK);
        }
        //validate if exists the stream platforms
        List<StreamingPlatform> streamingPlatforms = streamingPlatformRepository.findAllById(streamingPlatformIds);
        if (streamingPlatforms.size() != streamingPlatformIds.size()) {
            List<Long> missingIds = new ArrayList<>(streamingPlatformIds);
            missingIds.removeAll(streamingPlatforms.stream()
                    .map(StreamingPlatform::getId)
                    .toList());
            throw new NotFoundException("Streaming Platform not found with IDs: " + missingIds);
        }
        //Add only no duplicate values
        List<StreamingPlatform> streamingPlatformWithoutDuplicate = streamingPlatformRepository.findAllById(streamingPlatformIds)
                .stream().filter(streamingPlatform ->
                        !movie.getStreamingPlatforms().contains(streamingPlatform)).toList();

        movie.getStreamingPlatforms().addAll(streamingPlatformWithoutDuplicate);
        movieRepository.save(movie);

        return new ResponseEntity<>(new MovieDTO(movie), HttpStatus.OK);
    }

    public ResponseEntity<MovieDTO> assingDirectorToMovie(Long movieId, Long directorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie Not Found"));
        Director director = directorRepository.findById(directorId).orElseThrow(() -> new RuntimeException("Director Not Found"));

        movie.getDirectors().add(director);
        movieRepository.save(movie);
        return new ResponseEntity<>(new MovieDTO(movie), HttpStatus.OK);
    }

    public ResponseEntity<MovieDTO> assingMultipleDirectorToMovie(Long movieId, List<Long> directorIds) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie Not Found"));
        //Validate if the directors already exist in the movie
        if(directorIds.size() == movie.getDirectors().size()
                && movie.getDirectors().stream().allMatch(director -> directorIds.contains(director.getId()))) {
            return new ResponseEntity<>(new MovieDTO(movie), HttpStatus.OK);
        }
        //validate if the directors exist
        if(directorRepository.findAllById(directorIds).size() != directorIds.size()) {
            throw new NotFoundException("Directors not found");
        }
        //Create a list with no duplicate
        List<Director> directorsWithoutDuplicate = directorRepository.findAllById(directorIds).stream()
                .filter(director -> !movie.getDirectors().contains(director)).toList();
        movie.getDirectors().addAll(directorsWithoutDuplicate);

        movieRepository.save(movie);

        return new ResponseEntity<>(new MovieDTO(movie), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteMovie(Long id){
        if(movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
