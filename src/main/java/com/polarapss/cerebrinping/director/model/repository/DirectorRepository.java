package com.polarapss.cerebrinping.director.model.repository;

import com.polarapss.cerebrinping.director.model.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {
}
