package com.kama.jchatmind.model.response;

import com.kama.jchatmind.model.vo.AgentMemoryVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAgentMemoriesResponse {
    private AgentMemoryVO[] agentMemories;
}
