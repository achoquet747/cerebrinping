package com.polarapss.cerebrinping.streamingPlatform.controller;

import com.polarapss.cerebrinping.streamingPlatform.dto.StreamingPlatformDTO;
import com.polarapss.cerebrinping.streamingPlatform.service.StreamingPlatformService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/v1/movies/streaming_platforms")
public class StreamingPlatformController {

    private final StreamingPlatformService streamingPlatformService;

    @GetMapping
    public ResponseEntity<List<StreamingPlatformDTO>> getAllMovieTypes(){
        return streamingPlatformService.getAllStreamingPlatforms();
    }

    @PostMapping
    public ResponseEntity<StreamingPlatformDTO> createStreamingPlatform(@RequestBody StreamingPlatformDTO streamingPlatformDTO){
        return streamingPlatformService.createStreamingPlatform(streamingPlatformDTO);
    }
}
