package com.studysync.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assessment_id", nullable = false)
    private Assessment assessment;

    @Column(columnDefinition = "LONGTEXT")
    private String questionText;

    @Enumerated(EnumType.STRING)
    private QuestionType type; // MCQ, SHORT_ANSWER, CODE, ESSAY

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctAnswer;
    private String explanation;
    private Integer points = 1;
    private Integer difficultyLevel = 1;

    public enum QuestionType {
        MCQ, SHORT_ANSWER, CODE, ESSAY, TRUE_FALSE
    }
}
