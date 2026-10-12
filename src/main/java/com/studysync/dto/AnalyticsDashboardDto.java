package com.studysync.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnalyticsDashboardDto {

    private Integer totalStudyHoursCompleted;
    private Integer dailyStudyMinutes;
    private Integer weeklyStudyMinutes;
    private Integer currentStreakDays;
    private Integer totalXp;
    private Integer academicCompletionPercentage;
    private Integer careerRoadmapCompletionPercentage;
    private Double averageAssessmentScore;
    private Integer tasksCompletedToday;
    private Integer tasksScheduledToday;
}
