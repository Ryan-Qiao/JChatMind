package com.kama.jchatmind.model.entity;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class UserMemory {
    private String id;
    private String userId;
    private String sourceMessageId;
    private String memoryType;
    private String title;
    private String content;
    private Integer priority;
    private BigDecimal confidence;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastUsedAt;
}
