package com.dailycodebuffer.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * product-service
 * Elxan
 * 13.07.2023 16:14
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductServiceCustomException extends RuntimeException{
    private String errorCode;

    public ProductServiceCustomException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
