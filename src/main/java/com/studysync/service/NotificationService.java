package com.studysync.service;

import com.studysync.entity.Notification;
import com.studysync.entity.User;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotifications(User user);
    Notification markAsRead(Long id, User user);
    void sendDailyReminders();
}
