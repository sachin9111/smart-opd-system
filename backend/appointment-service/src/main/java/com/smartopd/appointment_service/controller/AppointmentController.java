package com.smartopd.appointment_service.controller;

import com.smartopd.appointment_service.dto.AppointmentResponse;
import com.smartopd.appointment_service.dto.CreateAppointmentRequest;
import com.smartopd.appointment_service.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentService appointmentService;

	@PostMapping
	public ResponseEntity<AppointmentResponse> createAppointment(@AuthenticationPrincipal Jwt jwt,
			@Valid @RequestBody CreateAppointmentRequest request) {

		Long patientUserId = ((Number) jwt.getClaim("userId")).longValue();

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(appointmentService.createAppointment(patientUserId, request));
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable("id") Long id) {

		return ResponseEntity.ok(appointmentService.getAppointment(id));
	}

	@GetMapping
	public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {

		return ResponseEntity.ok(appointmentService.getAllAppointments());
	}

	@GetMapping("/my")
	public ResponseEntity<List<AppointmentResponse>> myAppointments(@AuthenticationPrincipal Jwt jwt) {

		Long patientUserId = ((Number) jwt.getClaim("userId")).longValue();

		return ResponseEntity.ok(appointmentService.getPatientAppointments(patientUserId));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelAppointment(@PathVariable ("id") Long id) {

		appointmentService.cancelAppointment(id);

		return ResponseEntity.noContent().build();
	}
}