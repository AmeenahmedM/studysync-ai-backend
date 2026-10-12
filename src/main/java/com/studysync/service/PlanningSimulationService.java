package com.studysync.service;

import com.studysync.dto.SimulationRequest;
import com.studysync.dto.SimulationResponse;
import com.studysync.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlanningSimulationService {

    public SimulationResponse simulatePlan(User user, SimulationRequest request) {
        int projected = request.getSimulatedDailyHours() == null ? 2 : request.getSimulatedDailyHours();
        int missedDays = request.getMissedDays() == null ? 0 : request.getMissedDays();
        LocalDate targetDate = request.getSimulatedExamDate() == null ? LocalDate.now().plusDays(30) : request.getSimulatedExamDate();

        int projectedPercentage = Math.min(100, 70 + projected * 5 - missedDays * 2);
        String assessment = projectedPercentage >= 80 ? "ON_TIME" : projectedPercentage >= 60 ? "DELAYED" : "CRITICAL";

        return SimulationResponse.builder()
            .isSimulation(true)
            .projectedCompletionPercentage(projectedPercentage)
            .projectedCompletionDate(targetDate)
            .completionAssessment(assessment)
            .recommendation("Increase revision sessions and reduce non-core activities to recover schedule")
            .additionalHoursRequired(Math.max(0, 10 - projected))
            .build();
    }
}
