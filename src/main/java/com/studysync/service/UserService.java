package com.studysync.service;

import com.studysync.dto.UserDto;
import com.studysync.entity.User;

public interface UserService {
    User getAuthenticatedUser();
    UserDto getCurrentUserProfile();
    UserDto updateCurrentUserProfile(UserDto dto);
}
