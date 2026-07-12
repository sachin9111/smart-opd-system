package com.smartopd.queue_service.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.smartopd.queue_service.config.FeignConfig;
import com.smartopd.queue_service.dto.UserResponse;



@FeignClient(
	    name = "USER-SERVICE",
	    configuration = FeignConfig.class
	)
public interface UserServiceClient {

	
	@GetMapping("/api/v1/users/auth/{authUserId}")
	UserResponse getUser(@PathVariable("authUserId") Long authUserId);

}