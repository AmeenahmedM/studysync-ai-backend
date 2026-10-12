package com.studysync.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimulationResponse {

    private Boolean isSimulation = true;
    private Integer projectedCompletionPercentage;
    private LocalDate projectedCompletionDate;
    private String completionAssessment; // ON_TIME, DELAYED, CRITICAL
    private String recommendation;
    private Integer additionalHoursRequired;
}
