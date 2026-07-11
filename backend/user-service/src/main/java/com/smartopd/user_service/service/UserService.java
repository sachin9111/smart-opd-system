package com.smartopd.user_service.service;

import java.util.List;
import java.util.Optional;

import com.smartopd.user_service.dto.ProfileResponse;
import com.smartopd.user_service.dto.UserDto;
import com.smartopd.user_service.entity.User;
import com.smartopd.user_service.event.UserCreatedEvent;

public interface UserService {

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
    
    void createUser(UserCreatedEvent event);
    
    ProfileResponse getProfile(Long authUserId);
    
    UserDto getUserByAuthUserId(Long authUserId);
    
    

}