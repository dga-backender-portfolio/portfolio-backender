package org.dga.backend.portfolio.circuit_breaker.exception.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Business Exception
 */
@Data
@AllArgsConstructor
public class ErrorOpenCircuitBreakerException extends RuntimeException {
    private Integer codeService;
    private String detailError;
    private String state;
    private String failureRate;

}

