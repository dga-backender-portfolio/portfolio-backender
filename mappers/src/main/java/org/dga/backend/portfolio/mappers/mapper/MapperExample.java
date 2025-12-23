package org.dga.backend.portfolio.mappers.mapper;

import org.dga.backend.portfolio.mappers.api.dto.OutputMapperDto;
import org.dga.backend.portfolio.mappers.api.dto.OutputMapperSubDto;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper1;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

/*
La configuración más importante es:
 - componentModel: indica si se cargará el Mapper como un @Bean o se se accede a través de Mappers.getMapper(class)
 - uses: indica si se tienen que utilizar otros mappers o clases de utilidades
 - unmappedTargetPolicy: indica cómo se comporta entiempo de compilación si en el destino hay un campo no mapeado. Enum ReportingPolicy.ERROR/WARN/IGNORE
 - injectionStrategy: si es un @bean, indica cómo se inyectan los mappers definidos en la confiuración "uses. Enum InjectionStrategy.FIELD/CONSTRUCTOR (por CONSTRUCTOR sería la más adecuada).
 - nullValuePropertyMappingStrategy: qué hacer cuando el valor de origen es nulo. SET_TO_NULL (setear nulo en destino) o IGNORE (dejar el valor en destino). Muy útil para actualizaciones parciales.
 - imports: si se necesita alguna clase para las expresiones Java
 */
//@Mapper (componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Mapper
public interface MapperExample {
    MapperExample INSTANCE = Mappers.getMapper(MapperExample.class);

    //si no indicamos nada, los mapeos se realizan por reflexión, es decir, por nombre de atributo
    OutputMapperSubDto map (ModelMapper2 model);
    OutputMapperDto map (ModelMapper1 model1);

    @Mapping(target = "param4", source = "model2")
    //podemos invocar a métodos de mapeos, como incluir expresiones java de forma directa
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
