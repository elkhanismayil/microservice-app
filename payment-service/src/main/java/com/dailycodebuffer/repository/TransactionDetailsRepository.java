package com.dailycodebuffer.repository;

import com.dailycodebuffer.entity.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * service-registry
 * Elxan
 * 19.07.2023 11:47
 */
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    Optional<TransactionDetails> findByOrderId(long orderId);
}
