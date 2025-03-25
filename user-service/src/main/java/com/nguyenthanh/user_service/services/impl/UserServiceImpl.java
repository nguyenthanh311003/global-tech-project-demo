package com.nguyenthanh.user_service.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenthanh.user_service.dtos.requests.UserCreationRequest;
import com.nguyenthanh.user_service.dtos.responses.UserCreationResponse;
import com.nguyenthanh.user_service.entities.User;
import com.nguyenthanh.user_service.repositoties.UserRepository;
import com.nguyenthanh.user_service.services.IKafkaProducerService;
import com.nguyenthanh.user_service.services.IUserService;
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
public class UserServiceImpl implements IUserService {
    UserRepository userRepository;
    IKafkaProducerService kafkaProducerService;
    ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_TOPIC = "user-topic";
    @Override
    public UserCreationResponse create(UserCreationRequest userCreationRequest) {
        User newUser = User.builder()
                .id(UUID.randomUUID())
                .email(userCreationRequest.getEmail())
                .username(userCreationRequest.getUsername())
                .password(userCreationRequest.getPassword())
                .build();

        newUser = userRepository.save(newUser);

        UserCreationResponse response = UserCreationResponse.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .build();

        try {
            String message = objectMapper.writeValueAsString(response);
            kafkaProducerService.sendMessage(USER_TOPIC, message);
            log.info("Sent message Kafka: {}", message);
        } catch (Exception e) {
            log.error("Error to parse response to JSON", e);
        }

        return response;
    }
}
