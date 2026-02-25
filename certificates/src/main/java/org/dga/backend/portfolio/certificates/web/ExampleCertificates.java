package org.dga.backend.portfolio.certificates.web;

import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.certificates.api.dto.OutputDto;
import org.dga.backend.portfolio.certificates.exception.internal.ErrorWebClientException;
import org.dga.backend.portfolio.certificates.service.IExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientRequestException;

@Slf4j
@RestController
@RequestMapping("/api/example/certificates")
public class ExampleCertificates {

    @Autowired
    private IExampleService service;

    @GetMapping("/example1")
    public ResponseEntity<OutputDto> getExample1(){
        /*
        Invocamos a un servicio de negocio que internamente tiene un WebClient reactivo.
        Esta invocación requiere de uso de certificado cliente, mTls.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        //En ese caso, vamos a invocar al servicio que se encuentra ubicado en un sitio web NO CONFIABLE.
        //veremos que directamente, no se realiza ninguna petición, ya que la JVM al comprobar que no es un sitio confiable, no deja realizar la ejecución.

        OutputDto resultService = new OutputDto();
        try {
            //invocamos al servicio
            resultService.setStatusResponse("200");
            resultService.setResultResponse(
                    service.callBadSslAndReturnPkixError().block());
        }catch (ErrorWebClientException e){
            //obtenemos el fallo y lo mostramos en la response.
            //en este caso, aquí podremos ver claramente que el problema es el no envío de un certificado obligatorio
            resultService.setStatusResponse(String.valueOf(e.getCodeService()));
            resultService.setResultResponse(e.getDetailError());
        }catch (WebClientRequestException e ){
            //Si no se ha podido lanzar la request, tenemos el error, ya que la respuesta es que al no ser un sitio seguro, no permite la ejecución de la request.
            //Es por ello que no se captura la excepción previa, ya que se genera a partir de la ejecución y la obtención de una responses.
            resultService.setStatusResponse(null);
            resultService.setResultResponse(e.getMessage());
        }
        return new ResponseEntity<>(
                resultService,
                HttpStatus.OK);
    }
    @GetMapping("/example2")
    public ResponseEntity<OutputDto> getExample2(){
        /*
        Invocamos a un servicio de negocio que internamente tiene un WebClient reactivo.
        Esta invocación requiere de uso de certificado cliente, mTls.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        //En ese caso, vamos a invocar al servicio sin inyectarle el certificado necesario. El sitio, es un sitio seguro,
        //es decir, a través del trustStore de la jvm, se valida que el sitio es "seguro".

        OutputDto resultService = new OutputDto();
        try {
            //invocamos al servicio
            resultService.setStatusResponse("200");
            resultService.setResultResponse(
                    service.callBadSslWithoutCertificateReturnError().block());
        }catch (ErrorWebClientException e){
            //obtenemos el fallo y lo mostramos en la response.
            //en este caso, aquí podremos ver claramente que el problema es el no envío de un certificado obligatorio
            resultService.setStatusResponse(String.valueOf(e.getCodeService()));
            resultService.setResultResponse(e.getDetailError());
        }
        return new ResponseEntity<>(
                resultService,
                HttpStatus.OK);
    }
    @GetMapping("/example3")
    public ResponseEntity<OutputDto> getExample3(){
        /*
        Invocamos a un servicio de negocio que internamente tiene un WebClient reactivo.
        Esta invocación requiere de uso de certificado cliente, mTls.
        */

        //realizamos llamada a un servicio para recuperar la respuesta del servicio.
        //En este caso, el resultado es que realizamos la invocación de manera correcta, inyectando el certificado correctamente.

        OutputDto resultService = new OutputDto();
        try {
            //invocamos al servicio
            resultService.setStatusResponse("200");
            resultService.setResultResponse(
                    service.callBadSsl().block());
        }catch (ErrorWebClientException e){
            //obtenemos el fallo y lo mostramos en la response.
            //en este caso, no entraremos en esta excepción, ya que la respuesta del servicio es 200.
            resultService.setStatusResponse(String.valueOf(e.getCodeService()));
            resultService.setResultResponse(e.getDetailError());
        }
        return new ResponseEntity<>(
                resultService,
                HttpStatus.OK);
    }
}
