package com.dailycodebuffer.service;

import com.dailycodebuffer.entity.TransactionDetails;
import com.dailycodebuffer.model.PaymentMode;
import com.dailycodebuffer.model.PaymentRequest;
import com.dailycodebuffer.model.PaymentResponse;
import com.dailycodebuffer.repository.TransactionDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * service-registry
 * Elxan
 * 16.07.2023 23:13
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class PaymentServiceImpl implements PaymentService {
    private final TransactionDetailsRepository transactionDetailsRepository;

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details : {}", paymentRequest);
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .orderId(paymentRequest.getOrderId())
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .referenceNumber(paymentRequest.getReferenceNumber())
                .amount(paymentRequest.getAmount())
                .build();
        TransactionDetails saved = transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction Completed With Id : {}", saved.getId());
        return saved.getId();
    }

    @Override
    public PaymentResponse getPaymentDetailsByOrderId(long orderId) {
        log.info("Getting payment details for the order id : {}", orderId);
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return PaymentResponse.builder()
                .paymentId(transactionDetails.getId())
                .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
                .paymentDate(transactionDetails.getPaymentDate())
                .status(transactionDetails.getPaymentStatus())
                .orderId(transactionDetails.getOrderId())
                .amount(transactionDetails.getAmount())
                .build();
    }
}
