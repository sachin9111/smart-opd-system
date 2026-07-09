package com.smartopd.user_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartopd.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAuthUserId(Long authUserId);

    Optional<User> findByEmail(String email);
    
    boolean existsByAuthUserId(Long authUserId);

}