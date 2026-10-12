package com.studysync.service;

import com.studysync.dto.*;
import com.studysync.entity.*;
import com.studysync.exception.BadRequestException;
import com.studysync.exception.ResourceNotFoundException;
import com.studysync.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CareerRoadmapServiceImpl implements CareerRoadmapService {

    private final CareerRoadmapRepository careerRoadmapRepository;
    private final JobRoleRepository jobRoleRepository;
    private final UserSkillRepository userSkillRepository;
    private final SkillGapService skillGapService;

    @Override
    public CareerRoadmapDto generateRoadmap(User user, CareerRoadmapRequest request) {
        JobRole jobRole = jobRoleRepository.findById(request.getJobRoleId())
            .orElseThrow(() -> new ResourceNotFoundException("JobRole", "id", request.getJobRoleId()));

        CareerRoadmap roadmap = CareerRoadmap.builder()
            .user(user)
            .jobRole(jobRole)
            .name(request.getName() == null ? jobRole.getName() + " Roadmap" : request.getName())
            .durationDays(request.getDurationDays())
            .targetDate(request.getTargetDate() == null ? LocalDate.now().plusDays(request.getDurationDays()) : request.getTargetDate())
            .status(CareerRoadmap.Status.ACTIVE)
            .build();

        List<RoadmapPhase> phases = new ArrayList<>();
        String[] phaseNames = {"Foundation", "Core Practice", "Projects", "Interviews", "Revision"};
        for (int i = 0; i < phaseNames.length; i++) {
            RoadmapPhase phase = RoadmapPhase.builder()
                .roadmap(roadmap)
                .name(phaseNames[i])
                .phaseNumber(i + 1)
                .description("Phase " + (i + 1) + " for " + jobRole.getName())
                .startDate(LocalDate.now().plusDays(i * 14L))
                .endDate(LocalDate.now().plusDays((i + 1) * 14L))
                .completionPercentage(0)
                .build();
            phases.add(phase);
        }
        roadmap.setPhases(new HashSet<>(phases));

        for (RoadmapPhase phase : phases) {
            List<RoadmapTask> tasks = createTasksForPhase(phase, jobRole);
            phase.setTasks(new HashSet<>(tasks));
        }

        CareerRoadmap saved = careerRoadmapRepository.save(roadmap);
        return mapToDto(saved);
    }

    @Override
    public CareerRoadmapDto getRoadmapById(Long id, User user) {
        CareerRoadmap roadmap = careerRoadmapRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("CareerRoadmap", "id", id));
        if (!roadmap.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("Roadmap does not belong to this user");
        }
        return mapToDto(roadmap);
    }

    private List<RoadmapTask> createTasksForPhase(RoadmapPhase phase, JobRole jobRole) {
        List<RoadmapTask> tasks = new ArrayList<>();
        String[] taskNames = switch (phase.getPhaseNumber()) {
            case 1 -> new String[]{"Learn fundamentals", "Practice syntax", "Build mini exercises"};
            case 2 -> new String[]{"Solve DSA patterns", "Work on API projects", "Practice database queries"};
            case 3 -> new String[]{"Build portfolio project", "Document architecture", "Deploy project"};
            case 4 -> new String[]{"Interview drills", "Resume review", "Mock technical interviews"};
            default -> new String[]{"Revision", "Assessment", "Final review"};
        };

        for (String name : taskNames) {
            tasks.add(RoadmapTask.builder()
                .phase(phase)
                .name(name)
                .description("Task related to " + jobRole.getName())
                .taskType(RoadmapTask.TaskType.LEARNING)
                .targetDate(phase.getStartDate())
                .estimatedHours(3)
                .status(RoadmapTask.Status.PENDING)
                .build());
        }
        return tasks;
    }

    private CareerRoadmapDto mapToDto(CareerRoadmap roadmap) {
        return CareerRoadmapDto.builder()
            .id(roadmap.getId())
            .name(roadmap.getName())
            .jobRoleId(roadmap.getJobRole().getId())
            .durationDays(roadmap.getDurationDays())
            .targetDate(roadmap.getTargetDate())
            .completionPercentage(roadmap.getCompletionPercentage())
            .status(roadmap.getStatus().name())
            .createdAt(roadmap.getCreatedAt())
            .phases(roadmap.getPhases().stream().map(this::mapPhaseToDto).collect(Collectors.toSet()))
            .build();
    }

    private RoadmapPhaseDto mapPhaseToDto(RoadmapPhase phase) {
        return RoadmapPhaseDto.builder()
            .id(phase.getId())
            .name(phase.getName())
            .description(phase.getDescription())
            .phaseNumber(phase.getPhaseNumber())
            .startDate(phase.getStartDate())
            .endDate(phase.getEndDate())
            .completionPercentage(phase.getCompletionPercentage())
            .tasks(phase.getTasks().stream().map(this::mapTaskToDto).collect(Collectors.toSet()))
            .build();
    }

    private RoadmapTaskDto mapTaskToDto(RoadmapTask task) {
        return RoadmapTaskDto.builder()
            .id(task.getId())
            .name(task.getName())
            .description(task.getDescription())
            .taskType(task.getTaskType().name())
            .targetDate(task.getTargetDate())
            .estimatedHours(task.getEstimatedHours())
            .status(task.getStatus().name())
            .build();
    }
}
