package com.studysync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "learning_resources")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private ResourceType type; // VIDEO, ARTICLE, BOOK, COURSE, GITHUB, DOCUMENTATION

    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private String difficulty;
    private Long duration; // in seconds
    private Integer rating;

    public enum ResourceType {
        VIDEO, ARTICLE, BOOK, COURSE, GITHUB, DOCUMENTATION, TOOL, PRACTICE_PROBLEM
    }
}
