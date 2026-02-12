package org.dga.backend.portfolio.circuit_breaker.web;

import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.circuit_breaker.api.dto.OutputDto;
import org.dga.backend.portfolio.circuit_breaker.model.InformationModel;
import org.dga.backend.portfolio.circuit_breaker.service.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/example/circuit/breaker")
public class ExampleCircuitBreaker {

    @Autowired
    private IExampleService service;

    @GetMapping("/example1")
    public ResponseEntity<OutputDto> getExample1(@RequestParam Integer id){
        /*
        Invocamos a un servicio de negocio que internamente tiene un WebClient reactivo.
        Únicamente tratamos una respuesta, ya que el manejador de excepciones, es el que validará si ese webCliente funciona, no funciona
        o no se permite invocación porque el circuit breaker está abierto.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        InformationModel resultService = service.getInformation(id).block();
        log.info("Excution ok of services.");
        return new ResponseEntity<>(
                new OutputDto(resultService.getName(), resultService.getDocument(), resultService.getCategory()),
                HttpStatus.OK);
    }

}
