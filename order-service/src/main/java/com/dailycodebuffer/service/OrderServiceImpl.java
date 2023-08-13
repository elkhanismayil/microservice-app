package com.dailycodebuffer.service;

import com.dailycodebuffer.entity.Order;
import com.dailycodebuffer.exception.CustomException;
import com.dailycodebuffer.external.client.PaymentService;
import com.dailycodebuffer.external.client.ProductService;
import com.dailycodebuffer.external.request.PaymentRequest;
import com.dailycodebuffer.external.response.PaymentResponse;
import com.dailycodebuffer.model.OrderRequest;
import com.dailycodebuffer.external.response.OrderResponse;
import com.dailycodebuffer.external.response.ProductResponse;
import com.dailycodebuffer.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * service-registry
 * Elxan
 * 14.07.2023 16:32
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final PaymentService paymentService;

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing Order Request : {}", orderRequest);
        productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("Creating order with status CREATED");
        Order order = Order.builder()
                .amount(orderRequest.getTotalAmount())
                .orderStatus("CREATED")
                .productId(orderRequest.getProductId())
                .orderDate(Instant.now())
                .quantity(orderRequest.getQuantity())
                .build();
        Order saved = orderRepository.save(order);

        log.info("Calling payment service to complete the payments");
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(order.getId())
                .paymentMode(orderRequest.getPaymentMode())
                .amount(orderRequest.getTotalAmount())
                .build();

        String orderStatus;
        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment done successfully. Changing the order status to PLACED");
            orderStatus = "PLACED";
        } catch (Exception ex) {
            log.error("Error occurred in payment. Changing status to PAYMENT_FAILED");
            orderStatus = "PAYMENT_FAILED";
        }

        order.setOrderStatus(orderStatus);
        orderRepository.save(order);

        log.info("Order places successfully with order id : {}", order.getId());
        return saved.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get order details for order Id : {}", orderId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new CustomException("Order not found with this id : " + orderId, "NOT_FOUND", 404));

        log.info("Invoking product service to fetch the product for id : {}", order.getProductId());
        ProductResponse productResponse = productService.getProductById(order.getProductId());

        log.info("Getting payment information form the payment service");
        PaymentResponse paymentResponse = paymentService.getPaymentDetailsByOrderId(orderId);

        OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
                .productId(productResponse.getProductId())
                .productName(productResponse.getProductName())
                .build();

        OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
                .paymentId(paymentResponse.getPaymentId())
                .paymentMode(paymentResponse.getPaymentMode())
                .paymentStatus(paymentResponse.getStatus())
                .paymentDate(paymentResponse.getPaymentDate())
                .build();

        return OrderResponse.builder()
                .orderId(order.getId())
                .orderDate(order.getOrderDate())
                .orderStatus(order.getOrderStatus())
                .amount(order.getAmount())
                .productDetails(productDetails)
                .paymentDetails(paymentDetails)
                .build();
    }
}
