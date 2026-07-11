package com.smartopd.patient_service.service.impl;

import com.smartopd.patient_service.dto.CreatePatientRequest;
import com.smartopd.patient_service.dto.PatientDetailsResponse;
import com.smartopd.patient_service.dto.PatientResponse;
import com.smartopd.patient_service.dto.UserResponse;
import com.smartopd.patient_service.entity.Patient;
import com.smartopd.patient_service.exception.ResourceNotFoundException;
import com.smartopd.patient_service.feign.UserServiceClient;
import com.smartopd.patient_service.repository.PatientRepository;
import com.smartopd.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    
    private final UserServiceClient userServiceClient;

    @Override
    public PatientResponse createPatient(CreatePatientRequest request) {

        Patient patient = Patient.builder()
                .userId(request.getUserId())
                .bloodGroup(request.getBloodGroup())
                .height(request.getHeight())
                .weight(request.getWeight())
                .allergies(request.getAllergies())
                .medicalHistory(request.getMedicalHistory())
                .emergencyContactName(request.getEmergencyContactName())
                .emergencyContactNumber(request.getEmergencyContactNumber())
                .insuranceNumber(request.getInsuranceNumber())
                .active(request.getActive())
                .build();

        patientRepository.save(patient);

        return map(patient);
    }

    @Override
    public PatientDetailsResponse getPatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found"));

        return buildPatientDetails(patient);

    }

    @Override
    public List<PatientDetailsResponse> getAllPatients() {

        return patientRepository.findAll()
                .stream()
                .map(this::buildPatientDetails)
                .toList();

    }

    @Override
    public PatientResponse updatePatient(Long id,
                                         CreatePatientRequest request) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found"));

        patient.setUserId(request.getUserId());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setHeight(request.getHeight());
        patient.setWeight(request.getWeight());
        patient.setAllergies(request.getAllergies());
        patient.setMedicalHistory(request.getMedicalHistory());
        patient.setEmergencyContactName(request.getEmergencyContactName());
        patient.setEmergencyContactNumber(request.getEmergencyContactNumber());
        patient.setInsuranceNumber(request.getInsuranceNumber());
        patient.setActive(request.getActive());

        patientRepository.save(patient);

        return map(patient);
    }

    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found"));

        patientRepository.delete(patient);

    }

    private PatientResponse map(Patient patient) {

        return PatientResponse.builder()
                .id(patient.getId())
                .userId(patient.getUserId())
                .bloodGroup(patient.getBloodGroup())
                .height(patient.getHeight())
                .weight(patient.getWeight())
                .allergies(patient.getAllergies())
                .medicalHistory(patient.getMedicalHistory())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactNumber(patient.getEmergencyContactNumber())
                .insuranceNumber(patient.getInsuranceNumber())
                .active(patient.getActive())
                .build();
    }
    
    private PatientDetailsResponse buildPatientDetails(Patient patient) {

        UserResponse user =
                userServiceClient.getUser(patient.getUserId());

        return PatientDetailsResponse.builder()
                .id(patient.getId())
                .userId(patient.getUserId())

                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobile(user.getMobile())

                .bloodGroup(patient.getBloodGroup())
                .height(patient.getHeight())
                .weight(patient.getWeight())
                .allergies(patient.getAllergies())
                .medicalHistory(patient.getMedicalHistory())
                .emergencyContactName(patient.getEmergencyContactName())
                .emergencyContactNumber(patient.getEmergencyContactNumber())
                .insuranceNumber(patient.getInsuranceNumber())
                .active(patient.getActive())
                .build();

    }

}