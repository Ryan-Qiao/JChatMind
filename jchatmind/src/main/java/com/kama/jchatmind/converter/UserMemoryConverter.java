package com.kama.jchatmind.converter;

import com.kama.jchatmind.model.dto.UserMemoryDTO;
import com.kama.jchatmind.model.entity.UserMemory;
import com.kama.jchatmind.model.request.CreateUserMemoryRequest;
import com.kama.jchatmind.model.request.UpdateUserMemoryRequest;
import com.kama.jchatmind.model.vo.UserMemoryVO;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class UserMemoryConverter {

    public UserMemory toEntity(UserMemoryDTO dto) {
        Assert.notNull(dto, "UserMemoryDTO cannot be null");
        return UserMemory.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .sourceMessageId(dto.getSourceMessageId())
                .memoryType(dto.getMemoryType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .priority(dto.getPriority())
                .confidence(dto.getConfidence())
                .enabled(dto.getEnabled())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .lastUsedAt(dto.getLastUsedAt())
                .build();
    }

    public UserMemoryDTO toDTO(UserMemory entity) {
        Assert.notNull(entity, "UserMemory cannot be null");
        return UserMemoryDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .sourceMessageId(entity.getSourceMessageId())
                .memoryType(entity.getMemoryType())
                .title(entity.getTitle())
                .content(entity.getContent())
                .priority(entity.getPriority())
                .confidence(entity.getConfidence())
                .enabled(entity.getEnabled())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .lastUsedAt(entity.getLastUsedAt())
                .build();
    }

    public UserMemoryDTO toDTO(CreateUserMemoryRequest request) {
        Assert.notNull(request, "CreateUserMemoryRequest cannot be null");
        return UserMemoryDTO.builder()
                .userId(request.getUserId())
                .sourceMessageId(request.getSourceMessageId())
                .memoryType(request.getMemoryType())
                .title(request.getTitle())
                .content(request.getContent())
                .priority(request.getPriority())
                .confidence(request.getConfidence())
                .enabled(request.getEnabled())
                .build();
    }

    public UserMemoryVO toVO(UserMemoryDTO dto) {
        Assert.notNull(dto, "UserMemoryDTO cannot be null");
        return UserMemoryVO.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .sourceMessageId(dto.getSourceMessageId())
                .memoryType(dto.getMemoryType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .priority(dto.getPriority())
                .confidence(dto.getConfidence())
                .enabled(dto.getEnabled())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .lastUsedAt(dto.getLastUsedAt())
                .build();
    }

    public UserMemoryVO toVO(UserMemory entity) {
        return toVO(toDTO(entity));
    }

    public void updateDTOFromRequest(UserMemoryDTO dto, UpdateUserMemoryRequest request) {
        Assert.notNull(dto, "UserMemoryDTO cannot be null");
        Assert.notNull(request, "UpdateUserMemoryRequest cannot be null");

        if (request.getMemoryType() != null) {
            dto.setMemoryType(request.getMemoryType());
        }
        if (request.getTitle() != null) {
            dto.setTitle(request.getTitle());
        }
        if (request.getContent() != null) {
            dto.setContent(request.getContent());
        }
        if (request.getPriority() != null) {
            dto.setPriority(request.getPriority());
        }
        if (request.getConfidence() != null) {
            dto.setConfidence(request.getConfidence());
        }
        if (request.getEnabled() != null) {
            dto.setEnabled(request.getEnabled());
        }
    }
}
