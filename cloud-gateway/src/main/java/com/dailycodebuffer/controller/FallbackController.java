package com.dailycodebuffer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * service-registry
 * Elxan
 * 02.08.2023 15:50
 */
@RestController
public class FallbackController {

    @GetMapping("/orderServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String orderServiceFallback() {
        return "Order service is down!";
    }

    @GetMapping("/productServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String productServiceFallback() {
        return "Product service is down!";
    }

    @GetMapping("/paymentServiceFallBack")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public String paymentServiceFallback() {
        return "Payment service is down!";
    }
}
