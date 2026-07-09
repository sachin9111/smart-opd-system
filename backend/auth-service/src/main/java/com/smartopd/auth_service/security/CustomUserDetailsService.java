package com.smartopd.auth_service.security;

import com.smartopd.auth_service.entity.User;
import com.smartopd.auth_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository
                .findByEmailOrMobile(username, username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found."));

        return new CustomUserDetails(user);

    }

}