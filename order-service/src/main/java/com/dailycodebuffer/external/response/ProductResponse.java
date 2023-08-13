package com.dailycodebuffer.external.response;

import lombok.Builder;
import lombok.Data;

/**
 * service-registry
 * Elxan
 * 01.08.2023 15:21
 */
@Data
@Builder
public class ProductResponse {
    private long productId;
    private String productName;
    private long price;
    private long quantity;
}
