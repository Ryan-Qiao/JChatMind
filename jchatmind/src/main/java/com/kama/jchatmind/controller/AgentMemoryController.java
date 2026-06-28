package com.kama.jchatmind.controller;

import com.kama.jchatmind.model.common.ApiResponse;
import com.kama.jchatmind.model.request.CreateAgentMemoryRequest;
import com.kama.jchatmind.model.request.UpdateAgentMemoryRequest;
import com.kama.jchatmind.model.response.CreateAgentMemoryResponse;
import com.kama.jchatmind.model.response.GetAgentMemoriesResponse;
import com.kama.jchatmind.service.AgentMemoryFacadeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AgentMemoryController {
    private final AgentMemoryFacadeService agentMemoryFacadeService;

    @GetMapping("/agents/{agentId}/memories")
    public ApiResponse<GetAgentMemoriesResponse> getAgentMemories(@PathVariable String agentId) {
        return ApiResponse.success(agentMemoryFacadeService.getAgentMemoriesByAgentId(agentId));
    }

    @PostMapping("/agents/{agentId}/memories")
    public ApiResponse<CreateAgentMemoryResponse> createAgentMemory(
            @PathVariable String agentId,
            @RequestBody CreateAgentMemoryRequest request
    ) {
        return ApiResponse.success(agentMemoryFacadeService.createAgentMemory(agentId, request));
    }

    @PatchMapping("/agent-memories/{memoryId}")
    public ApiResponse<Void> updateAgentMemory(
            @PathVariable String memoryId,
            @RequestBody UpdateAgentMemoryRequest request
    ) {
        agentMemoryFacadeService.updateAgentMemory(memoryId, request);
        return ApiResponse.success();
    }

    @DeleteMapping("/agent-memories/{memoryId}")
    public ApiResponse<Void> deleteAgentMemory(@PathVariable String memoryId) {
        agentMemoryFacadeService.deleteAgentMemory(memoryId);
        return ApiResponse.success();
    }
}
