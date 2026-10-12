package com.studysync.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimulationRequest {

    private Long studyPlanId;
    private Integer simulatedDailyHours;
    private LocalDate simulatedExamDate;
    private Integer missedDays;
    private Boolean changeCareerDomain;
    private Long newJobRoleId;
}
