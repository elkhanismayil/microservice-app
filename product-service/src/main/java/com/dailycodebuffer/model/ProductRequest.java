package com.dailycodebuffer.model;

import lombok.Data;

/**
 * product-service
 * Elxan
 * 13.07.2023 12:24
 */
@Data
public class ProductRequest {
    private String name;
    private long price;
    private long quantity;
}
