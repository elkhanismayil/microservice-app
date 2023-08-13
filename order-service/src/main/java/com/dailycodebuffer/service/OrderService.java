package com.dailycodebuffer.service;

import com.dailycodebuffer.model.OrderRequest;
import com.dailycodebuffer.external.response.OrderResponse;

/**
 * service-registry
 * Elxan
 * 14.07.2023 16:32
 */
public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
