package com.dailycodebuffer.service;

import com.dailycodebuffer.model.PaymentRequest;
import com.dailycodebuffer.model.PaymentResponse;

/**
 * service-registry
 * Elxan
 * 16.07.2023 23:13
 */
public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
