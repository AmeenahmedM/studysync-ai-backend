package com.studysync.controller;

import com.studysync.dto.*;
import com.studysync.entity.User;
import com.studysync.service.SubjectService;
import com.studysync.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final UserService userService;

    @PostMapping("/subjects")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<SubjectDto>> createSubject(@Valid @RequestBody SubjectDto request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(subjectService.createSubject(request, user), "Subject created"));
    }

    @GetMapping("/subjects")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<SubjectDto>>> getSubjects() {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(subjectService.getSubjectsForCurrentUser(user), "Subjects retrieved"));
    }

    @PutMapping("/subjects/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<SubjectDto>> updateSubject(@PathVariable Long id, @RequestBody SubjectDto request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(subjectService.updateSubject(id, request, user), "Subject updated"));
    }

    @DeleteMapping("/subjects/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<Void>> deleteSubject(@PathVariable Long id) {
        User user = userService.getAuthenticatedUser();
        subjectService.deleteSubject(id, user);
        return ResponseEntity.ok(ApiResponse.success(null, "Subject deleted"));
    }

    @PostMapping("/subjects/{id}/topics")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<TopicDto>> addTopic(@PathVariable Long id, @RequestBody TopicDto request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(subjectService.addTopicToSubject(id, request, user), "Topic added"));
    }
}
