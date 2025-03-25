package com.nguyenthanh.user_service.services;

public interface IKafkaProducerService {
    void sendMessage(String topic, String message);
}
