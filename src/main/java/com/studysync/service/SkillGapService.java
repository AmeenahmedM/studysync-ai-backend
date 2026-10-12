package com.studysync.service;

import com.studysync.dto.SkillGapAnalysisDto;
import com.studysync.entity.User;

public interface SkillGapService {
    SkillGapAnalysisDto analyzeSkillGap(User user, Long jobRoleId);
}
