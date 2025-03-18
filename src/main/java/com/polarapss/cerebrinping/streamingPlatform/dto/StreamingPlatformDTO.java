package com.polarapss.cerebrinping.streamingPlatform.dto;

import com.polarapss.cerebrinping.streamingPlatform.model.entity.StreamingPlatform;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StreamingPlatformDTO {

    private Long id;
    private String name;

    public StreamingPlatformDTO(StreamingPlatform streamingPlatform) {
        this.id = streamingPlatform.getId();
        this.name = streamingPlatform.getName();
    }
}
