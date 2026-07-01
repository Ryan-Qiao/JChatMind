package com.kama.jchatmind.model.request;

import lombok.Data;

@Data
public class UpdateAgentMemoryRequest {
    private String memoryScope;
    private String memoryType;
    private String title;
    private String content;
    private Integer priority;
    private Boolean enabled;
}
