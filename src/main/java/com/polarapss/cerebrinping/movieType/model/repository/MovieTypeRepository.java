package com.polarapss.cerebrinping.movieType.model.repository;

import com.polarapss.cerebrinping.movieType.model.entity.MovieType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTypeRepository extends JpaRepository<MovieType, Long> {
}
