package org.dga.backend.portfolio.hierarchy.orm.mapper;

import org.dga.backend.portfolio.hierarchy.orm.entity.LegalPerson;
import org.dga.backend.portfolio.hierarchy.orm.entity.PhysicPerson;
import org.dga.backend.portfolio.hierarchy.orm.entity.ProfileFinanciability;
import org.dga.backend.portfolio.hierarchy.orm.entity.ProfileRisks;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Person;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Profile;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelPersonGet;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelProfileGet;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapperPerson {
    MapperPerson INSTANCE = Mappers.getMapper(MapperPerson.class);

    // Implementación por defecto para listas de Person
    List<ModelPersonGet> map(List<Person> all);
    @SubclassMapping(source = PhysicPerson.class, target = ModelPersonGet.class)
    @SubclassMapping(source = LegalPerson.class, target = ModelPersonGet.class)
    ModelPersonGet map (Person entity);

}
