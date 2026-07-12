package com.smartopd.queue_service.service;

import com.smartopd.queue_service.dto.QueueDashboardResponse;
import com.smartopd.queue_service.dto.QueueResponse;

import java.util.List;

public interface QueueService {

    List<QueueResponse> getDoctorQueue(Long doctorId);

    QueueResponse nextPatient(Long doctorId);

    QueueResponse completePatient(Long queueId);

    QueueResponse skipPatient(Long queueId);

    QueueResponse recallPatient(Long queueId);

    QueueDashboardResponse getDashboard(Long doctorId);
    
    List<QueueResponse> getPatientQueue(Long patientUserId);

}