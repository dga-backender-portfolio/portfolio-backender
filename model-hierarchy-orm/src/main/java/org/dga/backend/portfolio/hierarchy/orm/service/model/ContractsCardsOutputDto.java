package org.dga.backend.portfolio.hierarchy.orm.service.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ContractsCardsOutputDto implements Serializable {
    private String contract;
    private Long iban;
    private Boolean isActive;
    private Boolean isDeliquency;
    private Double maximumComissionAmount;
    private Double maximumRevolvingTae;
    private Double maximumRevolvingTin;

}
