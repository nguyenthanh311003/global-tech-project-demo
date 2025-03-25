package com.nguyenthanh.notification_service.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenthanh.notification_service.dtos.responses.UserCreationResponse;
import com.nguyenthanh.notification_service.models.NotificationDocument;
import com.nguyenthanh.notification_service.repositories.NotificationDocumentRepository;
import com.nguyenthanh.notification_service.services.IKafkaConsumerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KafkaConsumerServiceImpl implements IKafkaConsumerService {
    NotificationDocumentRepository notificationDocumentRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "user-topic", groupId = "consumer-group-crawled-data")
    @Override
    public void listen(String message) {
        log.info("Received message from Kafka: {}", message);
        try {
            UserCreationResponse response = objectMapper.readValue(message, UserCreationResponse.class);
            String activeMessage = "Account is active with username: " + response.getUsername();

            NotificationDocument notificationDocument = NotificationDocument.builder()
                    .id(String.valueOf(randomUUID()))
                    .message(activeMessage)
                    .build();

            notificationDocumentRepository.save(notificationDocument);
            log.info("Saved notification: {}", activeMessage);
        } catch (Exception e) {
            log.error("Error processing Kafka message", e);
        }
    }
}
