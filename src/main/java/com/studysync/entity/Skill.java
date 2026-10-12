package com.studysync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skills")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category; // TECHNICAL, SOFT, DOMAIN_SPECIFIC

    private String proficiencyLevel; // BEGINNER, INTERMEDIATE, ADVANCED, EXPERT

    @ManyToMany(mappedBy = "requiredSkills")
    private Set<JobRole> jobRoles = new HashSet<>();

    public enum Category {
        TECHNICAL, SOFT, DOMAIN_SPECIFIC
    }
}
