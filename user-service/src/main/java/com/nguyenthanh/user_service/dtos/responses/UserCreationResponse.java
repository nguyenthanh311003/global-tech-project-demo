package com.nguyenthanh.user_service.dtos.responses;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationResponse {
    UUID id;
    String username;
    String password;
    String email;
}
