package com.studysync.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillGapDto {

    private Long skillId;
    private String skillName;
    private String requiredLevel;
    private String currentLevel;
    private Integer priority; // 1-10, 10 being highest
    private Integer estimatedDaysToLearn;
    private String recommendation;
}
