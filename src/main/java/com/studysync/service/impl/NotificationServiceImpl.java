package com.studysync.service;

import com.studysync.entity.Notification;
import com.studysync.entity.User;
import com.studysync.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotifications(User user) {
        return notificationRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public Notification markAsRead(Long id, User user) {
        Notification notification = notificationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Notification not found"));
        if (!notification.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Notification does not belong to current user");
        }
        notification.setRead(true);
        return notificationRepository.save(notification);
    }

    @Override
    @Scheduled(cron = "0 0 8 * * *")
    public void sendDailyReminders() {
        // Scheduled notification processing placeholder for daily reminders
    }
}
