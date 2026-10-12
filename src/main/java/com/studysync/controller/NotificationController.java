package com.studysync.controller;

import com.studysync.dto.ApiResponse;
import com.studysync.entity.Notification;
import com.studysync.entity.User;
import com.studysync.service.NotificationService;
import com.studysync.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping("/notifications")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<List<Notification>>> getNotifications() {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(notificationService.getNotifications(user), "Notifications retrieved"));
    }

    @PutMapping("/notifications/{id}/read")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<ApiResponse<Notification>> markRead(@PathVariable Long id) {
        User user = userService.getAuthenticatedUser();
        return ResponseEntity.ok(ApiResponse.success(notificationService.markAsRead(id, user), "Notification marked as read"));
    }
}
