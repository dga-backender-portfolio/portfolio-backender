package org.dga.backend.portfolio.circuit_breaker.exception.internal;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Business Exception
 */
@Data
@AllArgsConstructor
public class ErrorGetInformationException extends RuntimeException {
    private String message;
}

