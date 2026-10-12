package com.studysync.controller;

import com.studysync.dto.*;
import com.studysync.entity.User;
import com.studysync.service.AnalyticsService;
import com.studysync.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final UserService userService;

    @GetMapping("/analytics/dashboard")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<AnalyticsDashboardDto>> dashboard() {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(analyticsService.getDashboard(user), "Dashboard analytics"));
    }

    @GetMapping("/analytics/weekly")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<Object>> getWeekly() {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(analyticsService.getWeeklyProgress(user), "Weekly progress"));
    }

    @GetMapping("/analytics/study-progress")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<String>> getStudyProgress() {
        return ResponseEntity.ok(ApiResponse.success("Study progress metrics available", "Study progress"));
    }

    @GetMapping("/analytics/career-progress")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<String>> getCareerProgress() {
        return ResponseEntity.ok(ApiResponse.success("Career progress metrics available", "Career progress"));
    }
}
