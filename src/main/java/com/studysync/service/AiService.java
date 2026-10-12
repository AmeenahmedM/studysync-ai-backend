package com.studysync.service;

import com.studysync.dto.AiChatRequest;
import com.studysync.dto.AiChatResponse;

public interface AiService {
    AiChatResponse chat(AiChatRequest request);
    String generateQuiz(String topic);
    String explainTopic(String topic);
    String recommendProjects(String domain);
}
