package com.kama.jchatmind.service;

import com.kama.jchatmind.model.dto.UserMemoryDTO;
import com.kama.jchatmind.model.request.CreateUserMemoryRequest;
import com.kama.jchatmind.model.request.UpdateUserMemoryRequest;
import com.kama.jchatmind.model.response.CreateUserMemoryResponse;
import com.kama.jchatmind.model.response.GetUserMemoriesResponse;

import java.util.List;

public interface UserMemoryFacadeService {
    GetUserMemoriesResponse getUserMemories();

    List<UserMemoryDTO> getEnabledGlobalUserMemories(int limit);

    CreateUserMemoryResponse createUserMemory(CreateUserMemoryRequest request);

    void updateUserMemory(String memoryId, UpdateUserMemoryRequest request);

    void deleteUserMemory(String memoryId);
}
