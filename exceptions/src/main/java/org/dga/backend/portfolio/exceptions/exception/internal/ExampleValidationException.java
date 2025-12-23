package org.dga.backend.portfolio.exceptions.exception.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Business Exception
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExampleValidationException extends RuntimeException {
    private Integer errorCode;
}

