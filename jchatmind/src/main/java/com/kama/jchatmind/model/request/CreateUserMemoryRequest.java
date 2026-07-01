package com.kama.jchatmind.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateUserMemoryRequest {
    private String userId;
    private String sourceMessageId;
    private String memoryType;
    private String title;
    private String content;
    private Integer priority;
    private BigDecimal confidence;
    private Boolean enabled;
}
