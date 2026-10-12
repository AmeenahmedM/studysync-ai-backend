package com.studysync.service;

import com.studysync.dto.AiChatRequest;
import com.studysync.dto.AiChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService {

    @Override
    public AiChatResponse chat(AiChatRequest request) {
        String answer = "StudySync AI fallback response: " + request.getMessage();
        if (request.getContext() != null && request.getContext().equalsIgnoreCase("CAREER_HELP")) {
            answer = "Career guidance recommendation: break the goal into weekly milestones, practice projects, and interview readiness drills.";
        }
        return AiChatResponse.builder()
            .message(answer)
            .context(request.getContext() == null ? "GENERAL" : request.getContext())
            .responseTime(System.currentTimeMillis())
            .build();
    }

    @Override
    public String generateQuiz(String topic) {
        return "Quiz for " + topic + ": 1) What is the core concept? 2) What trade-off matters? 3) What is one real-world example?";
    }

    @Override
    public String explainTopic(String topic) {
        return "Explanation: " + topic + " is best understood by studying fundamentals, real-world use cases, and hands-on examples.";
    }

    @Override
    public String recommendProjects(String domain) {
        return "Recommended project ideas for " + domain + ": build a mini dashboard app, automate a workflow, and ship a complete portfolio project.";
    }
}
