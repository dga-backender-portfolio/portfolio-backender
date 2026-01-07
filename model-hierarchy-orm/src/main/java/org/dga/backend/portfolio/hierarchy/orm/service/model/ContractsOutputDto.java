package org.dga.backend.portfolio.hierarchy.orm.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ContractsOutputDto implements Serializable {
    private Integer numLoans;
    private Integer numCards;
    private List<ContractsLoansOutputDto> loans;

    private List<ContractsCardsOutputDto> cards;

}
