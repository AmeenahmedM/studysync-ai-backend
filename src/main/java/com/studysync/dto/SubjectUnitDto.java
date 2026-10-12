package com.studysync.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectUnitDto {

    private Long id;

    @NotBlank(message = "Unit name is required")
    private String name;

    private String description;
    private Integer completionPercentage;
    private Set<TopicDto> topics;
}
