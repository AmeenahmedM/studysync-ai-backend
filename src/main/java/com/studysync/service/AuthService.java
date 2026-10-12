package com.studysync.service;

import com.studysync.dto.AuthRequest;
import com.studysync.dto.AuthResponse;
import com.studysync.dto.RegisterRequest;
import com.studysync.entity.User;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    User getCurrentUser();
}
