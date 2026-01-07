package org.dga.backend.portfolio.hierarchy.orm.service.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ContractsLoansOutputDto implements Serializable {
    private String contract;
    private Long iban;
    private Boolean isActive;
    private Boolean isDeliquency;
    private LocalDate paymentDueDate;
    private String paymentMethod;
}
