package com.dailycodebuffer.service;

import com.dailycodebuffer.model.ProductRequest;
import com.dailycodebuffer.model.ProductResponse;

/**
 * product-service
 * Elxan
 * 13.07.2023 11:03
 */
public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);

    void reduceQuantity(long productId, long quantity);
}
