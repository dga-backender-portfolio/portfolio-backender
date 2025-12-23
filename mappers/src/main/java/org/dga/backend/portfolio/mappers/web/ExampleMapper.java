package org.dga.backend.portfolio.mappers.web;

import org.dga.backend.portfolio.mappers.api.dto.OutputMapperDto;
import org.dga.backend.portfolio.mappers.mapper.MapperExample;
import org.dga.backend.portfolio.mappers.service.IExampleService;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper1;
import org.dga.backend.portfolio.mappers.service.model.ModelMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/example/mapper")
public class ExampleMapper {

    @Autowired
    private IExampleService service;

    @GetMapping("/example1")
    public ResponseEntity<OutputMapperDto> getExample1(){
        /*
        Para obtener los datos necesarios, debemos invocar a diferentes servicios de negocio.
        Por tanto, aunque quisieramos, el servicio de negocio no puede devolver el DTO necesario para la respuesta

        Para agrupar la información de estos servicios de negocio, generamos "modelos", que no dejan de ser
        POJOs, Plain Old Java Object --> Un objeto java sencillo y manejable
        */

        ModelMapper1 resultServ1 = service.getDataMapper();
        List<ModelMapper2> resultServ2 = service.getDataListMapper();

        /*
        Una vez tenemos todos los POJOs necesarios, los juntaremos en el DTO.
        ¿Cómo? A través de Mappers
         */
        OutputMapperDto result = MapperExample.INSTANCE.map(resultServ1, resultServ2);

        return new ResponseEntity(result, HttpStatus.OK);
    }
}
