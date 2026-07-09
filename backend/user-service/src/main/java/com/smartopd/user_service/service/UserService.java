package com.smartopd.user_service.service;

import java.util.List;

import com.smartopd.user_service.dto.UserDto;
import com.smartopd.user_service.event.UserCreatedEvent;

public interface UserService {

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();
    
    void createUser(UserCreatedEvent event);

}