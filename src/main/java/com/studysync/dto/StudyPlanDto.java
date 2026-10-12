package com.studysync.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudyPlanDto {

    private Long id;
    private String name;
    private String description;
    private String planType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer completionPercentage;
    private String status;
    private LocalDateTime createdAt;
    private Set<StudySessionDto> sessions;
}
