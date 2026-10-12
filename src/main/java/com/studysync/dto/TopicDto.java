package com.studysync.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDto {

    private Long id;

    @NotBlank(message = "Topic name is required")
    private String name;

    private String description;
    private String difficulty;
    private Integer estimatedHours;
    private Integer completionPercentage;
    private Integer priority;
}
