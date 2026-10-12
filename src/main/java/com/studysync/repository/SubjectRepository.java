package com.studysync.repository;

import com.studysync.entity.Subject;
import com.studysync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByUser(User user);

    @Query("SELECT s FROM Subject s JOIN FETCH s.units u WHERE s.user = :user")
    List<Subject> findByUserWithUnits(User user);
}
