package com.studysync.service;

import com.studysync.dto.StudyPlanDto;
import com.studysync.dto.StudyPlanRequest;
import com.studysync.dto.StudySessionDto;
import com.studysync.entity.User;

import java.util.List;

public interface StudyPlanService {
    StudyPlanDto generateStudyPlan(User user, StudyPlanRequest request);
    List<StudyPlanDto> getStudyPlansForUser(User user);
    StudyPlanDto getStudyPlanById(Long id, User user);
    StudyPlanDto regenerateStudyPlan(Long id, User user);
    StudySessionDto updateSession(Long sessionId, StudySessionDto dto, User user);
    StudySessionDto completeSession(Long sessionId, User user);
}
