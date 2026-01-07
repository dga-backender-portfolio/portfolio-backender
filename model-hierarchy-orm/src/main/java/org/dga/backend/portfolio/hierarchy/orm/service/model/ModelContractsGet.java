package org.dga.backend.portfolio.hierarchy.orm.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ModelContractsGet {
    //only a series of fields from each entity
    //contracts
    private String contract;
    private String contractType;
    private Long iban;
    private Boolean isActive;
    private Boolean isDeliquency;
    //loans
    private LocalDate paymentDueDate;
    private String paymentMethod;
    private String paymentModality;
    //cards
    private Double maximumRevolvingTae;
    private Double maximumRevolvingTin;

    public Boolean isLoan(){
        return contractType.equalsIgnoreCase("2")?true:false;
    }
    public Boolean isCard(){
        return contractType.equalsIgnoreCase("1")?true:false;
    }
}
