package com.dailycodebuffer.repository;

import com.dailycodebuffer.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * service-registry
 * Elxan
 * 14.07.2023 16:40
 */
public interface OrderRepository extends JpaRepository<Order, Long> {

}
