package com.studysync.service;

import com.studysync.dto.CareerRoadmapDto;
import com.studysync.dto.CareerRoadmapRequest;
import com.studysync.entity.User;

public interface CareerRoadmapService {
    CareerRoadmapDto generateRoadmap(User user, CareerRoadmapRequest request);
    CareerRoadmapDto getRoadmapById(Long id, User user);
}
