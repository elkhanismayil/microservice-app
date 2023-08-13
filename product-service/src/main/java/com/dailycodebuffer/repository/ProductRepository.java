package com.dailycodebuffer.repository;

import com.dailycodebuffer.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * product-service
 * Elxan
 * 13.07.2023 12:21
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
