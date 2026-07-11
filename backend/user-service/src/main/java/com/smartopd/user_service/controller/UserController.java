package com.smartopd.user_service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartopd.user_service.dto.ProfileResponse;
import com.smartopd.user_service.dto.UserDto;
import com.smartopd.user_service.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService   userService;


	
	
	@GetMapping("/profile")
	public ResponseEntity<ProfileResponse> profile(
	        @RequestHeader("X-User-Id") String userId) {

	    return ResponseEntity.ok(
	            userService.getProfile(Long.parseLong(userId))
	    );
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {

	    return ResponseEntity.ok(
	            userService.getAllUsers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUser(
	        @PathVariable("id") Long id) {

	    return ResponseEntity.ok(
	            userService.getUserById(id));

	}
	
	@GetMapping("/auth/{authUserId}")
	public ResponseEntity<UserDto> getByAuthUserId(@PathVariable("authUserId") Long authUserId) {

	    return ResponseEntity.ok(userService.getUserByAuthUserId(authUserId));
	}
}