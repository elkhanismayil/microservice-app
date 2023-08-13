package com.dailycodebuffer.controller;

import com.dailycodebuffer.model.OrderRequest;
import com.dailycodebuffer.external.response.OrderResponse;
import com.dailycodebuffer.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * service-registry
 * Elxan
 * 14.07.2023 16:33
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@Log4j2
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest) {
        long orderId = orderService.placeOrder(orderRequest);
        log.info("Order Id : {}", orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable("orderId") long orderId){
        OrderResponse orderResponse = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(orderResponse);
    }
}
