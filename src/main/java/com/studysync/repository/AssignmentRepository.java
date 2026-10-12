package com.studysync.repository;

import com.studysync.entity.Assignment;
import com.studysync.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findBySubject(Subject subject);
}
