package com.smartopd.doctor_service.service;

import com.smartopd.doctor_service.dto.CreateDoctorRequest;
import com.smartopd.doctor_service.dto.DoctorResponse;

import java.util.List;

public interface DoctorService {

    DoctorResponse createDoctor(CreateDoctorRequest request);

    DoctorResponse getDoctor(Long id);

    List<DoctorResponse> getAllDoctors();

    DoctorResponse updateDoctor(Long id,
                                CreateDoctorRequest request);

    void deleteDoctor(Long id);
}