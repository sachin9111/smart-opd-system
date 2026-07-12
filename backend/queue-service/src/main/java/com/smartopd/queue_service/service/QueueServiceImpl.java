package com.smartopd.queue_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.smartopd.queue_service.dto.QueueDashboardResponse;
import com.smartopd.queue_service.dto.QueueResponse;
import com.smartopd.queue_service.dto.UserResponse;
import com.smartopd.queue_service.entity.QueueEntry;
import com.smartopd.queue_service.enums.QueueStatus;
import com.smartopd.queue_service.feign.UserServiceClient;
import com.smartopd.queue_service.repository.QueueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QueueServiceImpl implements QueueService {

    private final QueueRepository queueRepository;

    private final UserServiceClient userServiceClient;
    
    @Override
    public List<QueueResponse> getDoctorQueue(Long doctorId) {

        return queueRepository
                .findByDoctorIdAndAppointmentDateOrderByTokenNumberAsc(
                        doctorId,
                        LocalDate.now())
                .stream()
                .map(this::buildQueueResponse)
                .toList();

    }
    
    @Override
    public List<QueueResponse> getPatientQueue(Long patientUserId){

        List<QueueEntry> list =
                queueRepository
                .findByPatientUserId(patientUserId);


        return list.stream()
                .map(this::buildQueueResponse)
                .toList();

    }
    
    @Override
    public QueueResponse nextPatient(Long doctorId) {

        // Check if a patient is already in consultation
        Optional<QueueEntry> currentPatient =
                queueRepository.findByDoctorIdAndAppointmentDateAndStatus(
                        doctorId,
                        LocalDate.now(),
                        QueueStatus.IN_PROGRESS
                );

        if (currentPatient.isPresent()) {
            throw new IllegalStateException(
                    "A patient is already in consultation. Complete or Skip first.");
        }

        QueueEntry nextPatient = queueRepository
                .findFirstByDoctorIdAndAppointmentDateAndStatusOrderByTokenNumberAsc(
                        doctorId,
                        LocalDate.now(),
                        QueueStatus.WAITING
                )
                .orElseThrow(() -> new ResourceNotFoundException("No waiting patient found."));

        nextPatient.setStatus(QueueStatus.IN_PROGRESS);

        queueRepository.save(nextPatient);

        return buildQueueResponse(nextPatient);
    }
    
    @Override
    public QueueResponse completePatient(Long queueId) {

        QueueEntry queue = queueRepository.findById(queueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue entry not found."));

        if (queue.getStatus() != QueueStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Only IN_PROGRESS patient can be completed.");
        }

        queue.setStatus(QueueStatus.COMPLETED);

        queueRepository.save(queue);

        return buildQueueResponse(queue);
    }
    
    @Override
    public QueueResponse skipPatient(Long queueId) {

        QueueEntry queue = queueRepository.findById(queueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue entry not found."));

        if (queue.getStatus() != QueueStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Only IN_PROGRESS patient can be skipped.");
        }

        queue.setStatus(QueueStatus.SKIPPED);

        queueRepository.save(queue);

        return buildQueueResponse(queue);
    }
    
    @Override
    public QueueResponse recallPatient(Long queueId) {

        QueueEntry queue = queueRepository.findById(queueId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Queue entry not found."));

        if (queue.getStatus() != QueueStatus.SKIPPED) {
            throw new IllegalStateException(
                    "Only skipped patient can be recalled.");
        }

        queue.setStatus(QueueStatus.WAITING);

        queueRepository.save(queue);

        return buildQueueResponse(queue);
    }
    
    @Override
    public QueueDashboardResponse getDashboard(Long doctorId) {

        Long waiting = queueRepository.countByDoctorIdAndAppointmentDateAndStatus(
                doctorId,
                LocalDate.now(),
                QueueStatus.WAITING);

        Long completed = queueRepository.countByDoctorIdAndAppointmentDateAndStatus(
                doctorId,
                LocalDate.now(),
                QueueStatus.COMPLETED);

        Long skipped = queueRepository.countByDoctorIdAndAppointmentDateAndStatus(
                doctorId,
                LocalDate.now(),
                QueueStatus.SKIPPED);

        Integer currentToken = queueRepository
                .findByDoctorIdAndAppointmentDateAndStatus(
                        doctorId,
                        LocalDate.now(),
                        QueueStatus.IN_PROGRESS)
                .map(QueueEntry::getTokenNumber)
                .orElse(0);

        Integer lastToken = queueRepository
                .findTopByDoctorIdAndAppointmentDateOrderByTokenNumberDesc(
                        doctorId,
                        LocalDate.now())
                .map(QueueEntry::getTokenNumber)
                .orElse(0);

        return QueueDashboardResponse.builder()
                .waiting(waiting)
                .completed(completed)
                .skipped(skipped)
                .currentToken(currentToken)
                .lastToken(lastToken)
                .build();
    }
    
    
    private QueueResponse buildQueueResponse(QueueEntry queue) {

        UserResponse user =
                userServiceClient.getUser(queue.getPatientUserId());

        return QueueResponse.builder()
                .id(queue.getId())
                .appointmentId(queue.getAppointmentId())
                .patientUserId(queue.getPatientUserId())
                .doctorId(queue.getDoctorId())
                .appointmentDate(queue.getAppointmentDate())
                .tokenNumber(queue.getTokenNumber())
                .status(queue.getStatus())

                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mobile(user.getMobile())
                .profilePhoto(user.getProfilePhoto())

                .build();
    }

}