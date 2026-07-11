package com.smartopd.patient_service.service;

import com.smartopd.patient_service.dto.CreatePatientRequest;
import com.smartopd.patient_service.dto.PatientDetailsResponse;
import com.smartopd.patient_service.dto.PatientResponse;

import java.util.List;

public interface PatientService {

    PatientResponse createPatient(CreatePatientRequest request);

    List<PatientDetailsResponse> getAllPatients();

    PatientResponse updatePatient(Long id, CreatePatientRequest request);

    void deletePatient(Long id);
    
    PatientDetailsResponse getPatient(Long id);

}