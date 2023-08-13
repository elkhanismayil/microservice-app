package com.dailycodebuffer.external.response;

import com.dailycodebuffer.model.PaymentMode;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

/**
 * service-registry
 * Elxan
 * 01.08.2023 11:15
 */
@Data
@Builder
public class OrderResponse {
    private long orderId;
    private Instant orderDate;
    private String orderStatus;
    private long amount;
    private ProductDetails productDetails;
    private PaymentDetails paymentDetails;

    @Data
    @Builder
    public static class ProductDetails {
        private long productId;
        private String productName;
        private long price;
        private long quantity;
    }

    @Data
    @Builder
    public static class PaymentDetails {
        private long paymentId;
        private PaymentMode paymentMode;
        private String paymentStatus;
        private Instant paymentDate;
    }
}
