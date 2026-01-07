package org.dga.backend.portfolio.hierarchy.orm.mapper;

import org.dga.backend.portfolio.hierarchy.orm.entity.ProfileFinanciability;
import org.dga.backend.portfolio.hierarchy.orm.entity.ProfileRisks;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Profile;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelProfileGet;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapperProfile {
    MapperProfile INSTANCE = Mappers.getMapper(MapperProfile.class);

    // Implementación por defecto para listas de Contracts
    List<ModelProfileGet> map(List<Profile> all);
    @SubclassMapping(source = ProfileFinanciability.class, target = ModelProfileGet.class)
    @SubclassMapping(source = ProfileRisks.class, target = ModelProfileGet.class)
    ModelProfileGet map (Profile entity);

}
