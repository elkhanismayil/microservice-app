package com.dailycodebuffer.external.response;

import com.dailycodebuffer.model.PaymentMode;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * service-registry
 * Elxan
 * 01.08.2023 18:19
 */
@Data
@Builder
public class PaymentResponse {
    private long paymentId;
    private String status;
    private PaymentMode paymentMode;
    private long amount;
    private Instant paymentDate;
    private long orderId;
}
