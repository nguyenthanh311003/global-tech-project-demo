package com.nguyenthanh.user_service.controllers;

import com.nguyenthanh.user_service.dtos.requests.UserCreationRequest;
import com.nguyenthanh.user_service.dtos.responses.UserCreationResponse;
import com.nguyenthanh.user_service.services.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserService userService;

    @PostMapping(value = "api/v1/users")
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody UserCreationRequest request) {
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }
}
