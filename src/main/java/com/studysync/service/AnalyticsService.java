package com.studysync.service;

import com.studysync.dto.AnalyticsDashboardDto;
import com.studysync.entity.ProgressRecord;
import com.studysync.entity.User;

import java.util.List;

public interface AnalyticsService {
    AnalyticsDashboardDto getDashboard(User user);
    List<ProgressRecord> getWeeklyProgress(User user);
}
