package com.smartopd.doctor_service.service.impl;

import com.smartopd.doctor_service.dto.CreateDoctorRequest;
import com.smartopd.doctor_service.dto.DoctorResponse;
import com.smartopd.doctor_service.entity.Doctor;
import com.smartopd.doctor_service.repository.DoctorRepository;
import com.smartopd.doctor_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository repository;

    @Override
    public DoctorResponse createDoctor(CreateDoctorRequest request) {

        if (repository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("Doctor already exists");
        }

        if (repository.existsByRegistrationNumber(
                request.getRegistrationNumber())) {

            throw new RuntimeException(
                    "Registration number already exists");
        }

        Doctor doctor = Doctor.builder()
                .userId(request.getUserId())
                .registrationNumber(request.getRegistrationNumber())
                .specialization(request.getSpecialization())
                .qualification(request.getQualification())
                .experience(request.getExperience())
                .department(request.getDepartment())
                .consultationFee(request.getConsultationFee())
                .active(true)
                .build();

        doctor = repository.save(doctor);

        return mapToResponse(doctor);
    }

    @Override
    public DoctorResponse getDoctor(Long id) {

        Doctor doctor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        return mapToResponse(doctor);
    }

    @Override
    public List<DoctorResponse> getAllDoctors() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public DoctorResponse updateDoctor(
            Long id,
            CreateDoctorRequest request) {

        Doctor doctor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        doctor.setRegistrationNumber(request.getRegistrationNumber());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setQualification(request.getQualification());
        doctor.setExperience(request.getExperience());
        doctor.setDepartment(request.getDepartment());
        doctor.setConsultationFee(request.getConsultationFee());

        doctor = repository.save(doctor);

        return mapToResponse(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {

        repository.deleteById(id);

    }

    private DoctorResponse mapToResponse(Doctor doctor) {

        return DoctorResponse.builder()
                .id(doctor.getId())
                .userId(doctor.getUserId())
                .registrationNumber(doctor.getRegistrationNumber())
                .specialization(doctor.getSpecialization())
                .qualification(doctor.getQualification())
                .experience(doctor.getExperience())
                .department(doctor.getDepartment())
                .consultationFee(doctor.getConsultationFee())
                .active(doctor.getActive())
                .build();
    }
}