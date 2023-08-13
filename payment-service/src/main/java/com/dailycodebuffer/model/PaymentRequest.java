package com.dailycodebuffer.model;

import lombok.Builder;
import lombok.Data;

/**
 * service-registry
 * Elxan
 * 19.07.2023 11:39
 */
@Data
@Builder
public class PaymentRequest {
    private long orderId;
    private long amount;
    private String referenceNumber;
    private PaymentMode paymentMode;
}
