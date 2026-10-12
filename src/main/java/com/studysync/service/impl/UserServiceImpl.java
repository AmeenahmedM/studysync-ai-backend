package com.studysync.service.impl;

import com.studysync.dto.UserDto;
import com.studysync.entity.User;
import com.studysync.exception.BadRequestException;
import com.studysync.repository.UserRepository;
import com.studysync.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new BadRequestException("Authenticated user not found"));
    }

    @Override
    public UserDto getCurrentUserProfile() {
        User user = getAuthenticatedUser();
        return mapToDto(user);
    }

    @Override
    public UserDto updateCurrentUserProfile(UserDto dto) {
        User user = getAuthenticatedUser();
        if (dto.getFirstName() != null) user.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        userRepository.save(user);
        return mapToDto(user);
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .role(user.getRole().name())
            .active(user.getActive())
            .createdAt(user.getCreatedAt())
            .updatedAt(user.getUpdatedAt())
            .build();
    }
}
