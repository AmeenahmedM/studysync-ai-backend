package com.studysync.service;

import com.studysync.dto.StudyPlanDto;
import com.studysync.dto.StudyPlanRequest;
import com.studysync.dto.StudySessionDto;
import com.studysync.entity.*;
import com.studysync.exception.BadRequestException;
import com.studysync.exception.ResourceNotFoundException;
import com.studysync.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyPlanServiceImpl implements StudyPlanService {

    private final StudyPlanRepository studyPlanRepository;
    private final SubjectRepository subjectRepository;
    private final StudySessionRepository studySessionRepository;

    @Override
    @Transactional
    public StudyPlanDto generateStudyPlan(User user, StudyPlanRequest request) {
        if (request.getSubjectIds() == null || request.getSubjectIds().isEmpty()) {
            throw new BadRequestException("At least one subject is required");
        }

        List<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds());
        List<Subject> ownedSubjects = subjects.stream()
            .filter(subject -> subject.getUser().getId().equals(user.getId()))
            .toList();
        if (ownedSubjects.size() != subjects.size()) {
            throw new BadRequestException("One or more subjects do not belong to this user");
        }

        StudyPlan plan = StudyPlan.builder()
            .user(user)
            .name(request.getPlanName() == null ? "Study plan " + LocalDate.now() : request.getPlanName())
            .description("Auto-generated academic study plan")
            .planType(StudyPlan.PlanType.ACADEMIC)
            .startDate(LocalDate.now())
            .endDate(request.getExamDate())
            .status(StudyPlan.Status.ACTIVE)
            .build();

        Set<StudySession> sessions = new HashSet<>();
        LocalDateTime startOfDay = LocalDate.now().atTime(9, 0);
        int sessionIndex = 0;

        for (Subject subject : ownedSubjects) {
            List<Topic> topics = subject.getUnits().stream()
                .flatMap(unit -> unit.getTopics().stream())
                .sorted(Comparator.comparingInt(Topic::getPriority).reversed())
                .toList();

            for (Topic topic : topics) {
                LocalDateTime scheduledStart = startOfDay.plusDays(sessionIndex).plusHours(sessionIndex % 3);
                LocalDateTime scheduledEnd = scheduledStart.plusHours(1);
                StudySession session = StudySession.builder()
                    .studyPlan(plan)
                    .topic(topic)
                    .scheduledStart(scheduledStart)
                    .scheduledEnd(scheduledEnd)
                    .sessionType(StudySession.SessionType.LEARNING)
                    .status(StudySession.Status.SCHEDULED)
                    .notes("Auto planned for " + subject.getName())
                    .build();
                sessions.add(session);
                sessionIndex++;
            }
        }

        plan.setSessions(sessions);
        StudyPlan saved = studyPlanRepository.save(plan);
        studySessionRepository.saveAll(sessions);

        return mapToDto(saved);
    }

    @Override
    public List<StudyPlanDto> getStudyPlansForUser(User user) {
        return studyPlanRepository.findByUser(user).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public StudyPlanDto getStudyPlanById(Long id, User user) {
        StudyPlan plan = studyPlanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("StudyPlan", "id", id));
        if (!plan.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("This plan does not belong to the current user");
        }
        return mapToDto(plan);
    }

    @Override
    public StudyPlanDto regenerateStudyPlan(Long id, User user) {
        StudyPlan plan = studyPlanRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("StudyPlan", "id", id));
        if (!plan.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Plan does not belong to the user");
        }
        plan.setStatus(StudyPlan.Status.ACTIVE);
        return mapToDto(studyPlanRepository.save(plan));
    }

    @Override
    public StudySessionDto updateSession(Long sessionId, StudySessionDto dto, User user) {
        StudySession session = studySessionRepository.findById(sessionId)
            .orElseThrow(() -> new ResourceNotFoundException("StudySession", "id", sessionId));
        if (!session.getStudyPlan().getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Session does not belong to the user");
        }
        if (dto.getScheduledStart() != null) session.setScheduledStart(dto.getScheduledStart());
        if (dto.getScheduledEnd() != null) session.setScheduledEnd(dto.getScheduledEnd());
        if (dto.getNotes() != null) session.setNotes(dto.getNotes());
        if (dto.getStatus() != null) session.setStatus(StudySession.Status.valueOf(dto.getStatus().toUpperCase()));
        return mapToSessionDto(studySessionRepository.save(session));
    }

    @Override
    public StudySessionDto completeSession(Long sessionId, User user) {
        StudySession session = studySessionRepository.findById(sessionId)
            .orElseThrow(() -> new ResourceNotFoundException("StudySession", "id", sessionId));
        if (!session.getStudyPlan().getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Session does not belong to the user");
        }
        session.setStatus(StudySession.Status.COMPLETED);
        session.setActualStart(session.getScheduledStart());
        session.setActualEnd(session.getScheduledEnd());
        return mapToSessionDto(studySessionRepository.save(session));
    }

    private StudyPlanDto mapToDto(StudyPlan plan) {
        return StudyPlanDto.builder()
            .id(plan.getId())
            .name(plan.getName())
            .description(plan.getDescription())
            .planType(plan.getPlanType().name())
            .startDate(plan.getStartDate())
            .endDate(plan.getEndDate())
            .completionPercentage(plan.getCompletionPercentage())
            .status(plan.getStatus().name())
            .createdAt(plan.getCreatedAt())
            .sessions(plan.getSessions().stream().map(this::mapToSessionDto).collect(Collectors.toSet()))
            .build();
    }

    private StudySessionDto mapToSessionDto(StudySession session) {
        return StudySessionDto.builder()
            .id(session.getId())
            .topicId(session.getTopic() == null ? null : session.getTopic().getId())
            .scheduledStart(session.getScheduledStart())
            .scheduledEnd(session.getScheduledEnd())
            .actualStart(session.getActualStart())
            .actualEnd(session.getActualEnd())
            .sessionType(session.getSessionType() == null ? null : session.getSessionType().name())
            .status(session.getStatus() == null ? null : session.getStatus().name())
            .notes(session.getNotes())
            .build();
    }
}
