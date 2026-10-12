package com.studysync.controller;

import com.studysync.dto.*;
import com.studysync.entity.User;
import com.studysync.service.StudyPlanService;
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
public class StudyPlanController {

    private final StudyPlanService studyPlanService;
    private final UserService userService;

    @PostMapping("/study-plans/generate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<StudyPlanDto>> generate(@Valid @RequestBody StudyPlanRequest request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(studyPlanService.generateStudyPlan(user, request), "Study plan generated"));
    }

    @GetMapping("/study-plans")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<StudyPlanDto>>> listPlans() {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(studyPlanService.getStudyPlansForUser(user), "Study plans retrieved"));
    }

    @GetMapping("/study-plans/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<StudyPlanDto>> getPlan(@PathVariable Long id) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(studyPlanService.getStudyPlanById(id, user), "Study plan retrieved"));
    }

    @PostMapping("/study-plans/{id}/regenerate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<StudyPlanDto>> regenerate(@PathVariable Long id) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(studyPlanService.regenerateStudyPlan(id, user), "Study plan regenerated"));
    }

    @PutMapping("/study-sessions/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<StudySessionDto>> updateSession(@PathVariable Long id, @RequestBody StudySessionDto request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(studyPlanService.updateSession(id, request, user), "Study session updated"));
    }

    @PostMapping("/study-sessions/{id}/complete")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<StudySessionDto>> completeSession(@PathVariable Long id) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(studyPlanService.completeSession(id, user), "Study session completed"));
    }
}
