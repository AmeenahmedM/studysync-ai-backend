package com.studysync.controller;

import com.studysync.dto.*;
import com.studysync.entity.User;
import com.studysync.service.PlanningSimulationService;
import com.studysync.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PlanningController {

    private final PlanningSimulationService planningSimulationService;
    private final UserService userService;

    @PostMapping("/planning/simulate")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<SimulationResponse>> simulate(@RequestBody SimulationRequest request) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(planningSimulationService.simulatePlan(user, request), "Simulation completed"));
    }
}
