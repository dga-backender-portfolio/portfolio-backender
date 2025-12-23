package org.dga.backend.portfolio.exceptions.exception;

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
public class ExampleExceptionRest extends Throwable implements Serializable {

  private Integer error;

  private String description;

  private String exception;

  private String exceptionMessage;

  private String classProducedError;

  private String methodProducedError;

  private Integer lineMethodProducedError;

  private String paramsRequest;
}

