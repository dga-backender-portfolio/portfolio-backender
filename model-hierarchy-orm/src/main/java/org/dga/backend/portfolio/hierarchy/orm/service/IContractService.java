package org.dga.backend.portfolio.hierarchy.orm.service;

import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelContractsGet;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IContractService {

    List<ModelContractsGet> getLoansContracts();

    List<ModelContractsGet> getCardsContracts();

    List<ModelContractsGet> getContracts();
}
