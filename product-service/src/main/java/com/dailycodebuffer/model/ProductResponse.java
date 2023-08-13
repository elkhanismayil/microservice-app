package com.dailycodebuffer.model;

import lombok.Builder;
import lombok.Data;

/**
 * product-service
 * Elxan
 * 13.07.2023 13:42
 */
@Data
@Builder
public class ProductResponse {
    private long productId;
    private String productName;
    private long price;
    private long quantity;
}
