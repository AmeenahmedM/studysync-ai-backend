package com.studysync.controller;

import com.studysync.dto.*;
import com.studysync.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/ai/chat")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<AiChatResponse>> chat(@RequestBody AiChatRequest request) {
        return ResponseEntity.ok(ApiResponse.success(aiService.chat(request), "AI response generated"));
    }

    @PostMapping("/ai/generate-quiz")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<String>> generateQuiz(@RequestParam String topic) {
        return ResponseEntity.ok(ApiResponse.success(aiService.generateQuiz(topic), "Quiz generated"));
    }

    @PostMapping("/ai/explain-topic")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<String>> explainTopic(@RequestParam String topic) {
        return ResponseEntity.ok(ApiResponse.success(aiService.explainTopic(topic), "Topic explanation generated"));
    }

    @PostMapping("/ai/recommend-projects")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<String>> recommendProjects(@RequestParam String domain) {
        return ResponseEntity.ok(ApiResponse.success(aiService.recommendProjects(domain), "Project recommendations generated"));
    }
}
