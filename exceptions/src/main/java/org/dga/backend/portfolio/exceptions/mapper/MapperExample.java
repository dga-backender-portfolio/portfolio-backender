package org.dga.backend.portfolio.exceptions.mapper;

import org.dga.backend.portfolio.exceptions.api.dto.OutputMapperDto;
import org.dga.backend.portfolio.exceptions.api.dto.OutputMapperSubDto;
import org.dga.backend.portfolio.exceptions.service.model.ModelMapper1;
import org.dga.backend.portfolio.exceptions.service.model.ModelMapper2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapperExample {
    MapperExample INSTANCE = Mappers.getMapper(MapperExample.class);


    OutputMapperSubDto map (ModelMapper2 model);
    OutputMapperDto map (ModelMapper1 model1);
    @Mapping(target = "param4", source = "model2")
    //@Mapping(target = "param3", source = "model2", qualifiedByName = "calculatedParam3")
    @Mapping(target = "param3", expression = "java(model2.size())")
    OutputMapperDto map (ModelMapper1 model1, List<ModelMapper2> model2);

    @Mapping(target = "param3", source = "model2")
    OutputMapperDto map (ModelMapper1 model1, Integer model2);
    @Named("calculatedParam3")
    static Integer fromMailUsage(List<ModelMapper2> model2) {
        return model2.size();
    }
}
