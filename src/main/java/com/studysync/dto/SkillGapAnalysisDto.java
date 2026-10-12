package com.studysync.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SkillGapAnalysisDto {

    private Long jobRoleId;
    private String jobRoleName;
    private List<SkillGapDto> skillGaps;
    private Integer estimatedPreparationDays;
    private String overallAssessment;
}
