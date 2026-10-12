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
public class CareerRoadmapDto {

    private Long id;
    private String name;
    private Long jobRoleId;
    private Integer durationDays;
    private LocalDate targetDate;
    private Integer completionPercentage;
    private String status;
    private LocalDateTime createdAt;
    private Set<RoadmapPhaseDto> phases;
}
