package com.smartopd.queue_service.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueueDashboardResponse {

    private Integer currentToken;

    private Integer lastToken;

    private Long waiting;

    private Long completed;

    private Long skipped;

}