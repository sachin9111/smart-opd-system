package com.smartopd.auth_service.repository;

import com.smartopd.auth_service.entity.RefreshToken;
import com.smartopd.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

   

    List<RefreshToken> findByUser(User user);

    List<RefreshToken> findByUserAndRevokedFalse(User user);

   
    
    Optional<RefreshToken> findByToken(String token);

    void deleteByUser(User user);

}