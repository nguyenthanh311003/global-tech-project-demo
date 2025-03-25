package com.nguyenthanh.notification_service.controllers;

import com.nguyenthanh.notification_service.models.NotificationDocument;
import com.nguyenthanh.notification_service.repositories.NotificationDocumentRepository;
import com.nguyenthanh.notification_service.services.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotificationController {
    INotificationService notificationService;

    @PostMapping("/api/v1/notifications")
    public void createNotification(@RequestParam(required = false) String message) {
        notificationService.create(message);
    }
}
