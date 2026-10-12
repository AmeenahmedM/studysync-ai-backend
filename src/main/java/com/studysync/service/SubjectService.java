package com.studysync.service;

import com.studysync.dto.SubjectDto;
import com.studysync.dto.TopicDto;
import com.studysync.entity.Subject;
import com.studysync.entity.SubjectUnit;
import com.studysync.entity.Topic;
import com.studysync.entity.User;

import java.util.List;

public interface SubjectService {
    SubjectDto createSubject(SubjectDto dto, User user);
    List<SubjectDto> getSubjectsForCurrentUser(User user);
    SubjectDto updateSubject(Long id, SubjectDto dto, User user);
    void deleteSubject(Long id, User user);
    TopicDto addTopicToSubject(Long subjectId, TopicDto dto, User user);
}
