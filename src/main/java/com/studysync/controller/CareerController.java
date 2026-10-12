package com.studysync.controller;

import com.studysync.dto.*;
import com.studysync.entity.User;
import com.studysync.service.CareerRoadmapService;
import com.studysync.service.SkillGapService;
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
public class CareerController {

    private final CareerRoadmapService careerRoadmapService;
    private final SkillGapService skillGapService;
    private final UserService userService;

    @GetMapping("/career-domains")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<String>>> getCareerDomains() {
        return ResponseEntity.ok(ApiResponse.success(List.of("Java Full Stack Development", "Python Development", "AI/ML", "Data Science", "Cybersecurity", "Cloud Computing", "DevOps", "Web Development"), "Career domains available"));
    }

    @PostMapping("/career-roadmaps/generate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<CareerRoadmapDto>> generateRoadmap(@Valid @RequestBody CareerRoadmapRequest request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(careerRoadmapService.generateRoadmap(user, request), "Career roadmap generated"));
    }

    @GetMapping("/career-roadmaps/{id}")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<CareerRoadmapDto>> getRoadmap(@PathVariable Long id) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(careerRoadmapService.getRoadmapById(id, user), "Career roadmap retrieved"));
    }

    @PostMapping("/skills/analyze")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<SkillGapAnalysisDto>> analyzeSkillGap(@Valid @RequestBody SkillGapRequest request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(skillGapService.analyzeSkillGap(user, request.getJobRoleId()), "Skill gap analysis completed"));
    }
}
