package org.dga.backend.portfolio.circuit_breaker.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Business Exception
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage", "message"})
public class CircuitBreakerExceptionRest extends RuntimeException implements Serializable {

  private Integer error;

  private String description;

  private String detailError;

  private String exception;

}

