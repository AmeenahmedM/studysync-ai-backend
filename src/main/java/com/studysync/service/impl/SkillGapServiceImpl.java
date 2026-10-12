package com.studysync.service;

import com.studysync.dto.SkillGapAnalysisDto;
import com.studysync.dto.SkillGapDto;
import com.studysync.entity.JobRole;
import com.studysync.entity.Skill;
import com.studysync.entity.User;
import com.studysync.entity.UserSkill;
import com.studysync.exception.BadRequestException;
import com.studysync.exception.ResourceNotFoundException;
import com.studysync.repository.JobRoleRepository;
import com.studysync.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillGapServiceImpl implements SkillGapService {

    private final JobRoleRepository jobRoleRepository;
    private final UserSkillRepository userSkillRepository;

    @Override
    public SkillGapAnalysisDto analyzeSkillGap(User user, Long jobRoleId) {
        JobRole jobRole = jobRoleRepository.findById(jobRoleId)
            .orElseThrow(() -> new ResourceNotFoundException("JobRole", "id", jobRoleId));

        Map<String, String> userSkillMap = userSkillRepository.findByUser(user).stream()
            .collect(Collectors.toMap(us -> us.getSkill().getName(), UserSkill::getProficiencyLevel, (a,b) -> a));

        List<SkillGapDto> gaps = new ArrayList<>();
        int totalDays = 0;
        for (Skill requiredSkill : jobRole.getRequiredSkills()) {
            String currentLevel = userSkillMap.getOrDefault(requiredSkill.getName(), "BEGINNER");
            int priority = computePriority(requiredSkill.getName(), currentLevel);
            int days = computeDays(currentLevel);
            totalDays += days;

            if (!currentLevel.equalsIgnoreCase("ADVANCED") && !currentLevel.equalsIgnoreCase("EXPERT")) {
                gaps.add(SkillGapDto.builder()
                    .skillId(requiredSkill.getId())
                    .skillName(requiredSkill.getName())
                    .requiredLevel(requiredSkill.getProficiencyLevel() == null ? "INTERMEDIATE" : requiredSkill.getProficiencyLevel())
                    .currentLevel(currentLevel)
                    .priority(priority)
                    .estimatedDaysToLearn(days)
                    .recommendation("Practice and build dedicated projects for " + requiredSkill.getName())
                    .build());
            }
        }

        return SkillGapAnalysisDto.builder()
            .jobRoleId(jobRole.getId())
            .jobRoleName(jobRole.getName())
            .skillGaps(gaps)
            .estimatedPreparationDays(Math.max(14, totalDays / Math.max(gaps.size(), 1)))
            .overallAssessment(gaps.isEmpty() ? "You are well aligned with this role" : "Focus on high-priority skill gaps first")
            .build();
    }

    private int computePriority(String skillName, String currentLevel) {
        return switch (currentLevel.toUpperCase()) {
            case "BEGINNER" -> 9;
            case "INTERMEDIATE" -> 6;
            case "ADVANCED" -> 3;
            default -> 2;
        };
    }

    private int computeDays(String currentLevel) {
        return switch (currentLevel.toUpperCase()) {
            case "BEGINNER" -> 21;
            case "INTERMEDIATE" -> 14;
            case "ADVANCED" -> 7;
            default -> 5;
        };
    }
}
