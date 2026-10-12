package com.studysync.service;

import com.studysync.dto.AnalyticsDashboardDto;
import com.studysync.entity.ProgressRecord;
import com.studysync.entity.User;
import com.studysync.repository.ProgressRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final ProgressRecordRepository progressRecordRepository;

    @Override
    public AnalyticsDashboardDto getDashboard(User user) {
        List<ProgressRecord> records = progressRecordRepository.findByUserOrderByRecordDateDesc(user);
        int weeklyMinutes = records.stream().mapToInt(r -> r.getWeeklyStudyMinutes() == null ? 0 : r.getWeeklyStudyMinutes()).sum();
        int dailyMinutes = records.stream().mapToInt(r -> r.getDailyStudyMinutes() == null ? 0 : r.getDailyStudyMinutes()).sum();
        int totalXp = records.stream().mapToInt(r -> r.getTotalXp() == null ? 0 : r.getTotalXp()).sum();
        int tasksCompleted = records.stream().mapToInt(r -> r.getTasksCompleted() == null ? 0 : r.getTasksCompleted()).sum();

        return AnalyticsDashboardDto.builder()
            .totalStudyHoursCompleted(Math.max(0, weeklyMinutes / 60))
            .dailyStudyMinutes(dailyMinutes)
            .weeklyStudyMinutes(weeklyMinutes)
            .currentStreakDays(records.isEmpty() ? 0 : Math.max(1, records.get(0).getCurrentStreak()))
            .totalXp(totalXp)
            .academicCompletionPercentage(65)
            .careerRoadmapCompletionPercentage(48)
            .averageAssessmentScore(78.0)
            .tasksCompletedToday(tasksCompleted)
            .tasksScheduledToday(Math.max(1, tasksCompleted + 2))
            .build();
    }

    @Override
    public List<ProgressRecord> getWeeklyProgress(User user) {
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusDays(7);
        return progressRecordRepository.findByUserAndRecordDateBetween(user, start, now);
    }
}
