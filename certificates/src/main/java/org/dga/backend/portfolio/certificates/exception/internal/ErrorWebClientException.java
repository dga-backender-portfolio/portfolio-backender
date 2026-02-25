package org.dga.backend.portfolio.certificates.exception.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Business Exception
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ErrorWebClientException extends RuntimeException {
    private Integer codeService;
    private String detailError;

}

