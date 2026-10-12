package com.studysync.repository;

import com.studysync.entity.User;
import com.studysync.entity.UserAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAvailabilityRepository extends JpaRepository<UserAvailability, Long> {
    List<UserAvailability> findByUser(User user);
}
