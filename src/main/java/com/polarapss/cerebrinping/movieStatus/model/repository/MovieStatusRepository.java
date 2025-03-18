package com.polarapss.cerebrinping.movieStatus.model.repository;

import com.polarapss.cerebrinping.movieStatus.model.entity.MovieStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieStatusRepository extends JpaRepository<MovieStatus, Long> {
}
