package com.studysync.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyPlanRequest {

    @NotEmpty(message = "At least one subject is required")
    private List<Long> subjectIds;

    @NotNull(message = "Exam date is required")
    private LocalDate examDate;

    @NotNull(message = "Daily study hours is required")
    private Integer dailyStudyHours;

    private String preferredStudyTimings;
    private List<Integer> unavailableDays; // 0=Sunday, 1=Monday, etc.
    private String planName;
}
