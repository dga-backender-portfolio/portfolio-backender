package org.dga.backend.portfolio.hierarchy.orm.service.impl;

import lombok.AllArgsConstructor;
import org.dga.backend.portfolio.hierarchy.orm.mapper.MapperContracts;
import org.dga.backend.portfolio.hierarchy.orm.repository.CardsRepository;
import org.dga.backend.portfolio.hierarchy.orm.repository.ContractsRepository;
import org.dga.backend.portfolio.hierarchy.orm.repository.LoansRepository;
import org.dga.backend.portfolio.hierarchy.orm.service.IContractService;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelContractsGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContractService implements IContractService {

    private ContractsRepository repoContracts;

    private LoansRepository repoLoans;

    private CardsRepository repoCards;

    public List<ModelContractsGet> getLoansContracts(){
        /**
         * Si revisamos la query utilizada, vemos que automáticamente, hibernate nos incluye el where
         *
         * Hibernate: select loans0_.id as id2_0_, loans0_.contract as contract3_0_, loans0_.currency as currency4_0_,
         * loans0_.failed_amount as failed_amount5_0_, loans0_.iban as iban6_0_, loans0_.is_active as is_active7_0_,
         * loans0_.is_deliquency as is_deliquency8_0_, loans0_.limit_amount as limit_amount9_0_,
         * loans0_.pending_amount as pending_amount10_0_, loans0_.risk_amount as risk_amount11_0_,
         * loans0_.payment_due_date as payment_due_date15_0_, loans0_.payment_method as payment_method16_0_,
         * loans0_.payment_modality as payment_modality17_0_, loans0_.percentage_tin as percentage_tin18_0_
         * from contracts loans0_
         * where loans0_.contract_type='2'
         */
        return MapperContracts.INSTANCE.mapListLoans(repoLoans.findAll());
    }

    public List<ModelContractsGet> getCardsContracts(){

        /**
         * Si revisamos la query utilizada, vemos que automáticamente, hibernate nos incluye el where
         *
         * Hibernate: select cards0_.id as id2_0_, cards0_.contract as contract3_0_, cards0_.currency as currency4_0_,
         * cards0_.failed_amount as failed_amount5_0_, cards0_.iban as iban6_0_, cards0_.is_active as is_active7_0_,
         * cards0_.is_deliquency as is_deliquency8_0_, cards0_.limit_amount as limit_amount9_0_,
         * cards0_.pending_amount as pending_amount10_0_, cards0_.risk_amount as risk_amount11_0_,
         * cards0_.maximum_comission_amount as maximum_comission12_0_, cards0_.maximum_revolving_tae as maximum_revolving13_0_,
         * cards0_.maximum_revolving_tin as maximum_revolving14_0_
         * from contracts cards0_
         * where cards0_.contract_type='1'
         */
        return MapperContracts.INSTANCE.mapListCards(repoCards.findAll());
    }

    public List<ModelContractsGet> getContracts(){
        /**
         * Si revisamos la query utilizada, simplemente se realiza un select all, y a través del mapper, informamos los diferentes campos.
         * Tenemos la flexibilidad que únicamente tenemos que modificar los campos en las entitys y modelos, sin realizar ninguna acción más --> ganancia en tiempo y complejidad.
         * Hibernate:
         *     select
         *         c1_0.id,
         *         c1_0.dtype,
         *         c1_0.contract,
         *         c1_0.currency,
         *         c1_0.failed_amount,
         *         c1_0.iban,
         *         c1_0.is_active,
         *         c1_0.is_deliquency,
         *         c1_0.limit_amount,
         *         c1_0.pending_amount,
         *         c1_0.risk_amount,
         *         c1_0.maximum_comission_amount,
         *         c1_0.maximum_revolving_tae,
         *         c1_0.maximum_revolving_tin,
         *         c1_0.payment_due_date,
         *         c1_0.payment_method,
         *         c1_0.payment_modality,
         *         c1_0.percentage_tin
         *     from
         *         contracts c1_0
         */
        return MapperContracts.INSTANCE.mapListContracts(repoContracts.findAll());
    }
}
