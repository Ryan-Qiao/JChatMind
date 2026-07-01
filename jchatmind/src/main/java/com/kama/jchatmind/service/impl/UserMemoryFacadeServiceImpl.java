package com.kama.jchatmind.service.impl;

import com.kama.jchatmind.converter.UserMemoryConverter;
import com.kama.jchatmind.exception.BizException;
import com.kama.jchatmind.mapper.UserMemoryMapper;
import com.kama.jchatmind.model.dto.UserMemoryDTO;
import com.kama.jchatmind.model.entity.UserMemory;
import com.kama.jchatmind.model.request.CreateUserMemoryRequest;
import com.kama.jchatmind.model.request.UpdateUserMemoryRequest;
import com.kama.jchatmind.model.response.CreateUserMemoryResponse;
import com.kama.jchatmind.model.response.GetUserMemoriesResponse;
import com.kama.jchatmind.model.vo.UserMemoryVO;
import com.kama.jchatmind.service.UserMemoryFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMemoryFacadeServiceImpl implements UserMemoryFacadeService {
    private static final String DEFAULT_MEMORY_TYPE = "preference";
    private static final int DEFAULT_PRIORITY = 0;
    private static final BigDecimal DEFAULT_CONFIDENCE = BigDecimal.ONE;

    private final UserMemoryMapper userMemoryMapper;
    private final UserMemoryConverter userMemoryConverter;

    @Override
    public GetUserMemoriesResponse getUserMemories() {
        List<UserMemory> memories = userMemoryMapper.selectAll();
        List<UserMemoryVO> result = new ArrayList<>();
        for (UserMemory memory : memories) {
            result.add(userMemoryConverter.toVO(memory));
        }
        return GetUserMemoriesResponse.builder()
                .userMemories(result.toArray(new UserMemoryVO[0]))
                .build();
    }

    @Override
    public List<UserMemoryDTO> getEnabledGlobalUserMemories(int limit) {
        List<UserMemory> memories = userMemoryMapper.selectEnabledGlobal(limit);
        List<String> usedMemoryIds = memories.stream()
                .map(UserMemory::getId)
                .filter(StringUtils::hasText)
                .toList();
        if (!usedMemoryIds.isEmpty()) {
            userMemoryMapper.markUsedByIds(usedMemoryIds);
        }
        return memories.stream()
                .map(userMemoryConverter::toDTO)
                .toList();
    }

    @Override
    public CreateUserMemoryResponse createUserMemory(CreateUserMemoryRequest request) {
        validateCreateRequest(request);

        UserMemoryDTO dto = userMemoryConverter.toDTO(request);
        normalizeDefaults(dto);

        LocalDateTime now = LocalDateTime.now();
        dto.setCreatedAt(now);
        dto.setUpdatedAt(now);

        UserMemory entity = userMemoryConverter.toEntity(dto);
        int result = userMemoryMapper.insert(entity);
        if (result <= 0) {
            throw new BizException("创建 User Memory 失败");
        }
        return CreateUserMemoryResponse.builder()
                .userMemoryId(entity.getId())
                .build();
    }

    @Override
    public void updateUserMemory(String memoryId, UpdateUserMemoryRequest request) {
        UserMemory existing = userMemoryMapper.selectById(memoryId);
        if (existing == null) {
            throw new BizException("User Memory 不存在: " + memoryId);
        }

        UserMemoryDTO dto = userMemoryConverter.toDTO(existing);
        userMemoryConverter.updateDTOFromRequest(dto, request);
        normalizeDefaults(dto);

        UserMemory updated = userMemoryConverter.toEntity(dto);
        updated.setId(existing.getId());
        int result = userMemoryMapper.updateById(updated);
        if (result <= 0) {
            throw new BizException("更新 User Memory 失败");
        }
    }

    @Override
    public void deleteUserMemory(String memoryId) {
        UserMemory existing = userMemoryMapper.selectById(memoryId);
        if (existing == null) {
            throw new BizException("User Memory 不存在: " + memoryId);
        }
        int result = userMemoryMapper.deleteById(memoryId);
        if (result <= 0) {
            throw new BizException("删除 User Memory 失败");
        }
    }

    private void validateCreateRequest(CreateUserMemoryRequest request) {
        if (request == null) {
            throw new BizException("User Memory 请求不能为空");
        }
        if (!StringUtils.hasText(request.getTitle())) {
            throw new BizException("User Memory 标题不能为空");
        }
        if (!StringUtils.hasText(request.getContent())) {
            throw new BizException("User Memory 内容不能为空");
        }
    }

    private void validateMemory(UserMemoryDTO dto) {
        if (!StringUtils.hasText(dto.getTitle())) {
            throw new BizException("User Memory 标题不能为空");
        }
        if (!StringUtils.hasText(dto.getContent())) {
            throw new BizException("User Memory 内容不能为空");
        }
        if (dto.getConfidence().compareTo(BigDecimal.ZERO) < 0
                || dto.getConfidence().compareTo(BigDecimal.ONE) > 0) {
            throw new BizException("User Memory 置信度必须在 0 到 1 之间");
        }
    }

    private void normalizeDefaults(UserMemoryDTO dto) {
        if (!StringUtils.hasText(dto.getMemoryType())) {
            dto.setMemoryType(DEFAULT_MEMORY_TYPE);
        }
        if (dto.getPriority() == null) {
            dto.setPriority(DEFAULT_PRIORITY);
        }
        if (dto.getConfidence() == null) {
            dto.setConfidence(DEFAULT_CONFIDENCE);
        }
        if (dto.getEnabled() == null) {
            dto.setEnabled(true);
        }
        dto.setTitle(dto.getTitle().trim());
        dto.setContent(dto.getContent().trim());
        dto.setMemoryType(dto.getMemoryType().trim());
        validateMemory(dto);
    }
}
