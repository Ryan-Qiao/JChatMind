package com.kama.jchatmind.model.request;

import lombok.Data;

@Data
public class CreateAgentMemoryRequest {
    private String sourceMessageId;
    private String memoryType;
    private String title;
    private String content;
    private Integer priority;
    private Boolean enabled;
}
