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
public class RoadmapTaskDto {

    private Long id;
    private String name;
    private String description;
    private String taskType;
    private LocalDate targetDate;
    private Integer estimatedHours;
    private String status;
}
