package com.studysync.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerRoadmapRequest {

    @NotNull(message = "Job role ID is required")
    private Long jobRoleId;

    @NotNull(message = "Duration in days is required")
    private Integer durationDays;

    private LocalDate targetDate;
    private Integer dailyPreparationHours;
    private String currentSkillLevel;
    private String name;
}
