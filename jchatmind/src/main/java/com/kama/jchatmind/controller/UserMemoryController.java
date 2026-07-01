package com.kama.jchatmind.controller;

import com.kama.jchatmind.model.common.ApiResponse;
import com.kama.jchatmind.model.request.CreateUserMemoryRequest;
import com.kama.jchatmind.model.request.UpdateUserMemoryRequest;
import com.kama.jchatmind.model.response.CreateUserMemoryResponse;
import com.kama.jchatmind.model.response.GetUserMemoriesResponse;
import com.kama.jchatmind.service.UserMemoryFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserMemoryController {
    private final UserMemoryFacadeService userMemoryFacadeService;

    @GetMapping("/user-memories")
    public ApiResponse<GetUserMemoriesResponse> getUserMemories() {
        return ApiResponse.success(userMemoryFacadeService.getUserMemories());
    }

    @PostMapping("/user-memories")
    public ApiResponse<CreateUserMemoryResponse> createUserMemory(
            @RequestBody CreateUserMemoryRequest request
    ) {
        return ApiResponse.success(userMemoryFacadeService.createUserMemory(request));
    }

    @PatchMapping("/user-memories/{memoryId}")
    public ApiResponse<Void> updateUserMemory(
            @PathVariable String memoryId,
            @RequestBody UpdateUserMemoryRequest request
    ) {
        userMemoryFacadeService.updateUserMemory(memoryId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/user-memories/{memoryId}")
    public ApiResponse<Void> deleteUserMemory(@PathVariable String memoryId) {
        userMemoryFacadeService.deleteUserMemory(memoryId);
        return ApiResponse.success();
    }
}
