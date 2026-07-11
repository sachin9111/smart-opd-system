package com.smartopd.doctor_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartopd.doctor_service.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByUserId(Long userId);

    Optional<Doctor> findByRegistrationNumber(String registrationNumber);

    boolean existsByUserId(Long userId);

    boolean existsByRegistrationNumber(String registrationNumber);
}