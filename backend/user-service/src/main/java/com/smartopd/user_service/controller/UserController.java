package com.smartopd.user_service.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartopd.user_service.dto.ProfileResponse;
import com.smartopd.user_service.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	
	@Autowired
	private UserService   userService;

//    @GetMapping("/profile")
//    public ResponseEntity<?> profile(@RequestHeader("X-User-Id") String userId,@RequestHeader("X-User-Email") String email,@RequestHeader("X-User-Roles") String roles) {
//    	
//
//        return ResponseEntity.ok(
//                Map.of(
//                        "userId", userId,
//                        "email", email,
//                        "roles", roles
//                )
//        );
//    }
	
	
	@GetMapping("/profile")
	public ResponseEntity<ProfileResponse> profile(
	        @RequestHeader("X-User-Id") String userId) {

	    return ResponseEntity.ok(
	            userService.getProfile(Long.parseLong(userId))
	    );
	}
}