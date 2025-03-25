package com.nguyenthanh.user_service.services;

import com.nguyenthanh.user_service.dtos.requests.UserCreationRequest;
import com.nguyenthanh.user_service.dtos.responses.UserCreationResponse;

public interface IUserService {
    UserCreationResponse create(UserCreationRequest userCreationRequest);
}
