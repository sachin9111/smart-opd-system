package com.smartopd.auth_service.repository;

import com.smartopd.auth_service.entity.PasswordResetOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetOtpRepository
        extends JpaRepository<PasswordResetOtp,Long>{

    Optional<PasswordResetOtp> findTopByEmailOrderByIdDesc(String email);

    void deleteByEmail(String email);

}