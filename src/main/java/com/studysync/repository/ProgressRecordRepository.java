package com.studysync.repository;

import com.studysync.entity.ProgressRecord;
import com.studysync.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProgressRecordRepository extends JpaRepository<ProgressRecord, Long> {
    List<ProgressRecord> findByUserOrderByRecordDateDesc(User user);
    List<ProgressRecord> findByUserAndRecordDateBetween(User user, LocalDate start, LocalDate end);
}
