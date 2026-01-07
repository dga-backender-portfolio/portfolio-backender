package org.dga.backend.portfolio.hierarchy.orm.mapper;

import org.dga.backend.portfolio.hierarchy.orm.entity.Cards;
import org.dga.backend.portfolio.hierarchy.orm.entity.Loans;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Contracts;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ContractsCardsOutputDto;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ContractsLoansOutputDto;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ContractsOutputDto;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelContractsGet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MapperContracts {
    MapperContracts INSTANCE = Mappers.getMapper(MapperContracts.class);

    List<ModelContractsGet> mapListLoans (List<Loans> list);

    List<ModelContractsGet> mapListCards(List<Cards> all);

    //List<ModelContractsGet> mapListContracts(List<Contracts> all);
    // Implementación por defecto para listas de Contracts
    List<ModelContractsGet> mapListContracts(List<Contracts> all);
    @SubclassMapping(source = Loans.class, target = ModelContractsGet.class)
    @SubclassMapping(source = Cards.class, target = ModelContractsGet.class)
    ModelContractsGet map (Contracts entity);

    ContractsOutputDto map (ModelContractsGet model);
/*
En el caso de que se permitiera autogenerar un map entre un elemento iterado y otro no iterado, podríamos realizar el mismo
código a través de las anotaciones:
    @Mapping(target = "numLoans", expression = "java(contractsModel.stream().filter(ModelContractsGet::isLoan).count())")
    @Mapping(target = "numCards", expression = "java(contractsModel.stream().filter(ModelContractsGet::isCard).count())")
    @Mapping(target = "loans", source = "java(contractsModel.stream().filter(ModelContractsGet::isLoan).map(l->mapModelToDtoLoans(l)).collect(Collectors.toList()))")
    @Mapping(target = "cards", source = "java(contractsModel.stream().filter(ModelContractsGet::isCard).map(c->mapModelToDtoCards(c)).collect(Collectors.toList()))")
*/
default ContractsOutputDto map(List<ModelContractsGet> contractsModel) {
        return new ContractsOutputDto(
                (int) contractsModel.stream().filter(ModelContractsGet::isLoan).count(), (int) contractsModel.stream().filter(ModelContractsGet::isCard).count(),
                contractsModel.stream().filter(ModelContractsGet::isLoan).map(l->mapModelToDtoLoans(l)).collect(Collectors.toList()),
                contractsModel.stream().filter(ModelContractsGet::isCard).map(c->mapModelToDtoCards(c)).collect(Collectors.toList())
        );
    }

    ContractsLoansOutputDto mapModelToDtoLoans (ModelContractsGet loans);
    ContractsCardsOutputDto mapModelToDtoCards (ModelContractsGet cards);


}
