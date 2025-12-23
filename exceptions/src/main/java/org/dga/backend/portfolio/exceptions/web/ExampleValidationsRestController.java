package org.dga.backend.portfolio.exceptions.web;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.dga.backend.portfolio.exceptions.api.dto.InputDto;
import org.dga.backend.portfolio.exceptions.api.dto.OutputDto;
import org.dga.backend.portfolio.exceptions.exception.internal.ExampleValidationException;
import org.dga.backend.portfolio.exceptions.service.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/example/validations")
public class ExampleValidationsRestController {

    @Autowired
    private IExampleService service;

    @GetMapping("/example1")
    public ResponseEntity<OutputDto> getExample1(@RequestBody InputDto input) throws ExampleValidationException {
        /*
        Apartado de validación de input
        No tiene sentido ejecutar el negocio, si la información no es válida
         */

        /*
        Siempre podemos tener una serie de validaciones genéricas,
        como pueden ser los datos nulos, si no los hemos especificado como obligatorios
        o el propio valor de los campos, ya que según el objetivo del endpoint, ciertos valores pueden
        no tener sentido.
        Luego, vendrán las validaciones propias de la validez de la información, como será asegurar que un dni existe, por ejemplo
         */
            //validación 1: que la fecha sea menor o igual que la del día
        if(Objects.nonNull(input.getDateRequest()) && input.getDateRequest().isAfter(LocalDate.now())){
            throw new ExampleValidationException(400);

        }
            //validación 2: al menos uno del customerId o el customerInternalId esté informado
            // nulo o valor a un número: ObjectUtils.defaultIfNull(num, 0) == 0
            // Optional.ofNullable(input.getCustomerInternalId()).orElse(0) == 0
        if(!StringUtils.isNotBlank(input.getCustomerId()) && ObjectUtils.defaultIfNull(input.getCustomerInternalId(), 0) == 0){
            throw new ExampleValidationException(401);
        }
        /*
        Si la información es válida, se ejecuta el negocio
        Este negocio podrá ser una única llamada a una lógica de negocio, o bien varias,
        según sea de reutilizable el código, o se requieran hacer validaciones parciales de información
         */
        //en este ejemplo, vemos que la evaluación de la respuesta del servicio de negocio, queda en la capa web, porque es la que aplica si es correcta o incorrecta
        //esta respuesta. Imagina que hay un endpoint que da de alta si no hay datos. Si en la capa de negocio realizaramos esta lógica, tendríamso que duplicar
        //el método, ya que el mismo resultado del negocio, según el endpoint invocado, tiene un significado distinto.
        List<String> returnService1 = service.getInformation();

        /*
        El negocio, nunca trabajará con los dto's --> Es decir, siempre tienen que ser generados a nivel de capa web, bien sea manualmente o a través de mappers
         */
        if(returnService1.size() == 0){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity(new OutputDto(returnService1, "Here is your information", null), HttpStatus.OK);
        }

    }
}
