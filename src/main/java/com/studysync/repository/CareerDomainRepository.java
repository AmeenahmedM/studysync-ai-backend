package com.studysync.repository;

import com.studysync.entity.CareerDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerDomainRepository extends JpaRepository<CareerDomain, Long> {
    Optional<CareerDomain> findByName(String name);
}
