package com.studysync.repository;

import com.studysync.entity.UserSkill;
import com.studysync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {
    List<UserSkill> findByUser(User user);
}
