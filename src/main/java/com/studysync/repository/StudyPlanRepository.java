package com.studysync.repository;

import com.studysync.entity.StudyPlan;
import com.studysync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyPlanRepository extends JpaRepository<StudyPlan, Long> {
    List<StudyPlan> findByUser(User user);
}
