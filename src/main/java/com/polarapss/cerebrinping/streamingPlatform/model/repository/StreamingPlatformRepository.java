package com.polarapss.cerebrinping.streamingPlatform.model.repository;

import com.polarapss.cerebrinping.streamingPlatform.model.entity.StreamingPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingPlatformRepository extends JpaRepository<StreamingPlatform, Long> {
}
