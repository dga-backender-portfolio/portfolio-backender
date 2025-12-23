package org.dga.backend.portfolio.exceptions.web;

import org.dga.backend.portfolio.exceptions.api.dto.InputDto;
import org.dga.backend.portfolio.exceptions.api.dto.OutputDto;
import org.dga.backend.portfolio.exceptions.api.dto.OutputMapperDto;
import org.dga.backend.portfolio.exceptions.exception.ExampleExceptionRest;
import org.dga.backend.portfolio.exceptions.mapper.MapperExample;
import org.dga.backend.portfolio.exceptions.service.interfaz.IExampleService;
import org.dga.backend.portfolio.exceptions.service.model.ModelMapper1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/example/exception")
public class ExampleExceptionRestController {

    @Autowired
    private IExampleService service;
    private static final Logger log = LoggerFactory.getLogger(ExampleExceptionRestController.class);
    @GetMapping("/example1")
    public ResponseEntity<OutputDto> getExample1(@RequestBody InputDto input) throws ExampleExceptionRest {
        /*
        durante la ejecución del endpoint, invocamos a diferentes servicios.
        Alguno de ellos puede lanzar una excepción. Podemos tenerla tratada por código con try/catch
        o bien podemos tener un manejador. En este caso, hemos creado uno --> RestResponseEntityExceptionHandler.class
        */

        ModelMapper1 resultServ1 = service.getDataMapper();
        /*aquí lanzaremos una excepción que hemos controlado dentro de un try-catch
            De esta forma, veremos que el manejador solo actua a nivel del RestoController
         */
        try {
            Integer resultServExcp = service.generateExceptionExample();
            //si no falla, haríamos el mapper
            OutputMapperDto result = MapperExample.INSTANCE.map(resultServ1, resultServExcp);
            //y devolveríamos el resutlado
            return new ResponseEntity(result, HttpStatus.OK);
        }catch(NullPointerException e){
            //A través del log, podemos ver que primero se muestra ese log, y posteriormente el capturado en el manejador
            log.error("Captura try-cacht @RestController. Primero se captura en la implementación, ya que el manejador está a nivel de @RestController");
            //lanzamos la excepción para ser capturada por el manejador
            throw e;
        }
    }
    @GetMapping("/example2")
    public ResponseEntity<OutputDto> getExample2(@RequestBody InputDto input) throws ExampleExceptionRest {
        /*
         Mismo caso que el ejemplo anterior, pero esta vez sin disponer de un try catch.
         Con ello, comprobamos como la excepción es capturada por el manejador, para siempre, siempre responder con la excepción
         definida en el throws
         */
        ModelMapper1 resultServ1 = service.getDataMapper();
        service.generateExceptionTimeoutExample();
        /*
        No llegaremos a este punto, ya que se lanzará una excepción no controlada en el servicio de negocio
         */
        OutputMapperDto result = MapperExample.INSTANCE.map(resultServ1);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @GetMapping("/example3")
    public ResponseEntity getExample3(@RequestBody InputDto input) throws ExampleExceptionRest {
        /*
        En este caso, la excepción es "dinámica". El servicio de negocio lanzará diferentes excepciones sin patrón para ello,
        y además, son excepciones no declaradas en el manejador, por lo que deberán ir hacia el manejador de la excepción
        general Exception.class
        */

        service.generateRandomException();

        /*
        Vemos que aún sin tener tratada la excepción en el manejador, podemos "controlar" toda respuesta no tenida en cuenta.
         */

        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/example4")
    public ResponseEntity getExample4() throws ExampleExceptionRest {
        /*
        Cuando lanzamos una excepción, también es posible trazas y obtener las cabeceras utilizadas
        */

        service.generateRandomException();

        /*
        Al capturar la excepción, vemos que nos aparece la información de las cabeceras de la petición en el logger de error. No así en la responses.
         */

        return new ResponseEntity(HttpStatus.OK);
    }
}
