package com.smartopd.queue_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartopd.queue_service.entity.QueueEntry;

public interface QueueRepository
        extends JpaRepository<QueueEntry, Long> {

}