package com.studysync.service.impl;

import com.studysync.dto.SubjectDto;
import com.studysync.dto.TopicDto;
import com.studysync.entity.Subject;
import com.studysync.entity.SubjectUnit;
import com.studysync.entity.Topic;
import com.studysync.entity.User;
import com.studysync.exception.BadRequestException;
import com.studysync.exception.ResourceNotFoundException;
import com.studysync.repository.SubjectRepository;
import com.studysync.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public SubjectDto createSubject(SubjectDto dto, User user) {
        Subject subject = Subject.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .user(user)
            .difficulty(dto.getDifficulty() == null ? null : Subject.DifficultyLevel.valueOf(dto.getDifficulty().toUpperCase()))
            .examinationDate(dto.getExaminationDate())
            .targetScore(dto.getTargetScore())
            .completionPercentage(dto.getCompletionPercentage() == null ? 0 : dto.getCompletionPercentage())
            .priority(dto.getPriority() == null ? 5 : dto.getPriority())
            .build();
        Subject saved = subjectRepository.save(subject);
        return mapToDto(saved);
    }

    @Override
    public List<SubjectDto> getSubjectsForCurrentUser(User user) {
        return subjectRepository.findByUser(user).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubjectDto updateSubject(Long id, SubjectDto dto, User user) {
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You do not own this subject");
        }
        subject.setName(dto.getName() == null ? subject.getName() : dto.getName());
        subject.setDescription(dto.getDescription() == null ? subject.getDescription() : dto.getDescription());
        if (dto.getDifficulty() != null) {
            subject.setDifficulty(Subject.DifficultyLevel.valueOf(dto.getDifficulty().toUpperCase()));
        }
        subject.setExaminationDate(dto.getExaminationDate() == null ? subject.getExaminationDate() : dto.getExaminationDate());
        subject.setTargetScore(dto.getTargetScore() == null ? subject.getTargetScore() : dto.getTargetScore());
        subject.setCompletionPercentage(dto.getCompletionPercentage() == null ? subject.getCompletionPercentage() : dto.getCompletionPercentage());
        subject.setPriority(dto.getPriority() == null ? subject.getPriority() : dto.getPriority());
        return mapToDto(subjectRepository.save(subject));
    }

    @Override
    @Transactional
    public void deleteSubject(Long id, User user) {
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", id));
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You do not own this subject");
        }
        subjectRepository.delete(subject);
    }

    @Override
    @Transactional
    public TopicDto addTopicToSubject(Long subjectId, TopicDto dto, User user) {
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "id", subjectId));
        if (!subject.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("You do not own this subject");
        }

        SubjectUnit unit = subject.getUnits().stream().findFirst().orElseGet(() -> {
            SubjectUnit newUnit = SubjectUnit.builder()
                .name("General Unit")
                .subject(subject)
                .build();
            subject.getUnits().add(newUnit);
            return newUnit;
        });

        Topic topic = Topic.builder()
            .name(dto.getName())
            .description(dto.getDescription())
            .unit(unit)
            .difficulty(dto.getDifficulty() == null ? null : Subject.DifficultyLevel.valueOf(dto.getDifficulty().toUpperCase()))
            .estimatedHours(dto.getEstimatedHours() == null ? 1 : dto.getEstimatedHours())
            .completionPercentage(dto.getCompletionPercentage() == null ? 0 : dto.getCompletionPercentage())
            .priority(dto.getPriority() == null ? 5 : dto.getPriority())
            .build();
        unit.getTopics().add(topic);
        return mapToTopicDto(topic);
    }

    private SubjectDto mapToDto(Subject subject) {
        return SubjectDto.builder()
            .id(subject.getId())
            .name(subject.getName())
            .description(subject.getDescription())
            .difficulty(subject.getDifficulty() == null ? null : subject.getDifficulty().name())
            .examinationDate(subject.getExaminationDate())
            .targetScore(subject.getTargetScore())
            .completionPercentage(subject.getCompletionPercentage())
            .priority(subject.getPriority())
            .build();
    }

    private TopicDto mapToTopicDto(Topic topic) {
        return TopicDto.builder()
            .id(topic.getId())
            .name(topic.getName())
            .description(topic.getDescription())
            .difficulty(topic.getDifficulty() == null ? null : topic.getDifficulty().name())
            .estimatedHours(topic.getEstimatedHours())
            .completionPercentage(topic.getCompletionPercentage())
            .priority(topic.getPriority())
            .build();
    }
}
