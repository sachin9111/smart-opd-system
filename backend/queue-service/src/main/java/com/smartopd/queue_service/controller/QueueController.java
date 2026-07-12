package com.smartopd.queue_service.controller;

import com.smartopd.queue_service.dto.QueueDashboardResponse;
import com.smartopd.queue_service.dto.QueueResponse;
import com.smartopd.queue_service.service.QueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class QueueController {

    private final QueueService queueService;

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<QueueResponse>> getDoctorQueue(
            @PathVariable("doctorId") Long doctorId) {

        return ResponseEntity.ok(
                queueService.getDoctorQueue(doctorId));
    }

    @PutMapping("/doctor/{doctorId}/next")
    public ResponseEntity<QueueResponse> nextPatient(
            @PathVariable("doctorId") Long doctorId) {

        return ResponseEntity.ok(
                queueService.nextPatient(doctorId));
    }

    @PutMapping("/{queueId}/complete")
    public ResponseEntity<QueueResponse> completePatient(
            @PathVariable("queueId") Long queueId) {

        return ResponseEntity.ok(
                queueService.completePatient(queueId));
    }

    @PutMapping("/{queueId}/skip")
    public ResponseEntity<QueueResponse> skipPatient(
            @PathVariable("queueId") Long queueId) {

        return ResponseEntity.ok(
                queueService.skipPatient(queueId));
    }

    @PutMapping("/{queueId}/recall")
    public ResponseEntity<QueueResponse> recallPatient(
            @PathVariable("queueId") Long queueId) {

        return ResponseEntity.ok(
                queueService.recallPatient(queueId));
    }

    @GetMapping("/doctor/{doctorId}/dashboard")
    public ResponseEntity<QueueDashboardResponse> getDashboard(
            @PathVariable("doctorId") Long doctorId) {

        return ResponseEntity.ok(
                queueService.getDashboard(doctorId));
    }
    
    
    @GetMapping("/my")
    public ResponseEntity<List<QueueResponse>> myQueue(
            @AuthenticationPrincipal Jwt jwt) {


        Long patientUserId =
            ((Number)jwt.getClaim("userId")).longValue();


        return ResponseEntity.ok(
                queueService.getPatientQueue(patientUserId)
        );

    }
    

}