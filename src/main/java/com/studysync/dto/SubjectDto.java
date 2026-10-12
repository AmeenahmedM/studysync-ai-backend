package com.studysync.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto {

    private Long id;

    @NotBlank(message = "Subject name is required")
    private String name;

    private String description;
    private String difficulty;
    private LocalDate examinationDate;
    private Integer targetScore;
    private Integer completionPercentage;
    private Integer priority;
    private Set<SubjectUnitDto> units;
}
