package com.nguyenthanh.notification_service.services.impl;

import com.nguyenthanh.notification_service.models.NotificationDocument;
import com.nguyenthanh.notification_service.repositories.NotificationDocumentRepository;
import com.nguyenthanh.notification_service.services.INotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotificationServiceImpl implements INotificationService {
    NotificationDocumentRepository notificationDocumentRepository;
    @Override
    public void create(String message) {
        NotificationDocument notificationDocument = NotificationDocument.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .message(message).build();
        notificationDocumentRepository.save(notificationDocument);
    }
}
