package com.polarapss.cerebrinping.streamingPlatform.service;

import com.polarapss.cerebrinping.streamingPlatform.dto.StreamingPlatformDTO;
import com.polarapss.cerebrinping.streamingPlatform.model.entity.StreamingPlatform;
import com.polarapss.cerebrinping.streamingPlatform.model.repository.StreamingPlatformRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class StreamingPlatformService {

    private final StreamingPlatformRepository streamingPlatformRepository;

    public ResponseEntity<StreamingPlatformDTO> createStreamingPlatform(StreamingPlatformDTO streamingPlatformDTO) {
        StreamingPlatform streamingPlatform = new StreamingPlatform();
        streamingPlatform.setName(streamingPlatformDTO.getName());

        StreamingPlatform newStreamingPlatform = streamingPlatformRepository.save(streamingPlatform);

        streamingPlatformDTO.setId(newStreamingPlatform.getId());

        return new ResponseEntity<>(streamingPlatformDTO, HttpStatus.CREATED);
    }

    public ResponseEntity<List<StreamingPlatformDTO>> getAllStreamingPlatforms(){
        List<StreamingPlatform> streamingPlatforms = streamingPlatformRepository.findAll();
        List<StreamingPlatformDTO> streamingPlatformDTOS = new ArrayList<>();
        for (StreamingPlatform streamingPlatform : streamingPlatforms) {
            streamingPlatformDTOS.add(new StreamingPlatformDTO(streamingPlatform));
        }
        return new ResponseEntity<>(streamingPlatformDTOS, HttpStatus.OK);
    }
}
