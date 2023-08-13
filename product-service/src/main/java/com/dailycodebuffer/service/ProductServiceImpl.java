package com.dailycodebuffer.service;

import com.dailycodebuffer.entity.Product;
import com.dailycodebuffer.exception.ProductServiceCustomException;
import com.dailycodebuffer.model.ProductRequest;
import com.dailycodebuffer.model.ProductResponse;
import com.dailycodebuffer.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * product-service
 * Elxan
 * 13.07.2023 11:03
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product");
        Product product = Product.builder()
                .productName(productRequest.getName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("Product Completed");
        return savedProduct.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Get the product by product id : {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product not found with given product id", "PRODUCT_NOT_FOUND"));
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quantity {} for id : {}", quantity, productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product not found with this id : " + productId,
                        "PRODUCT_NOT_FOUND"));

        if (product.getQuantity() < quantity) {
            throw new ProductServiceCustomException("Product doesn't have sufficient quantity", "INSUFFICIENT_QUANTITY");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("Product quantity updated successfully");
    }
}
