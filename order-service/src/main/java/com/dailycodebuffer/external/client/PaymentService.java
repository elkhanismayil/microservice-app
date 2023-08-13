package com.dailycodebuffer.external.client;

import com.dailycodebuffer.exception.CustomException;
import com.dailycodebuffer.external.request.PaymentRequest;
import com.dailycodebuffer.external.response.PaymentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * service-registry
 * Elxan
 * 19.07.2023 23:06
 */
@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {
    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    @GetMapping("/order/{orderId}")
    PaymentResponse getPaymentDetailsByOrderId(@PathVariable("orderId") long orderId);

    default void fallback(Exception e) {
        throw new CustomException("Payment service is unavailable", "SERVICE_UNAVAILABLE", 503);
    }
}
