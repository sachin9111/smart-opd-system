package com.smartopd.patient_service.controller;

import com.smartopd.patient_service.dto.CreatePatientRequest;
import com.smartopd.patient_service.dto.PatientDetailsResponse;
import com.smartopd.patient_service.dto.PatientResponse;
import com.smartopd.patient_service.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

	private final PatientService patientService;

	@PostMapping
	public ResponseEntity<PatientResponse> createPatient(@RequestBody CreatePatientRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatientDetailsResponse> getPatient(@PathVariable("id") Long id) {
        System.out.println("methode called");
		return ResponseEntity.ok(patientService.getPatient(id));
	}

	@GetMapping
	public ResponseEntity<List<PatientDetailsResponse>> getAllPatients() {

		return ResponseEntity.ok(patientService.getAllPatients());
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientResponse> updatePatient(@PathVariable("id") Long id,
			@RequestBody CreatePatientRequest request) {

		return ResponseEntity.ok(patientService.updatePatient(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {

		patientService.deletePatient(id);

		return ResponseEntity.noContent().build();
	}

}