package com.dailycodebuffer.model;

import lombok.Builder;
import lombok.Data;

/**
 * service-registry
 * Elxan
 * 14.07.2023 18:53
 */
@Data
@Builder
public class OrderRequest {
    private long productId;
    private long totalAmount;
    private long quantity;
    private PaymentMode paymentMode;
}
