package com.kama.jchatmind.model.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AgentMemoryVO {
    private String id;
    private String agentId;
    private String sourceMessageId;
    private String memoryScope;
    private String memoryType;
    private String title;
    private String content;
    private Integer priority;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastUsedAt;
}
