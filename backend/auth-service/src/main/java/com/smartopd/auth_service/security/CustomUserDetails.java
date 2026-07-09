package com.smartopd.auth_service.security;

import com.smartopd.auth_service.entity.User;
import com.smartopd.auth_service.entity.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return user.getUserRoles()
                .stream()
                .map(userRole ->
                        new SimpleGrantedAuthority(
                                userRole.getRole().getCode().name()))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Spring Security username.
     * We use email internally.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getStatus().name().equals("ACTIVE");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(user.isEnabled());
    }

}