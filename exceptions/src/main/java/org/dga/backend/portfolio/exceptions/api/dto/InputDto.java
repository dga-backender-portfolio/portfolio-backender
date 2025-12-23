package org.dga.backend.portfolio.exceptions.api.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class InputDto implements Serializable {
    private Integer customerInternalId;
    private String customerId;
    private String customerName;
    private LocalDate dateRequest;
}
