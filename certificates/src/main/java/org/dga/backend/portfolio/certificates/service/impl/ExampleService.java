package org.dga.backend.portfolio.certificates.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.certificates.exception.internal.ErrorWebClientException;
import org.dga.backend.portfolio.certificates.service.IExampleService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleService implements IExampleService {

    //webclient apuntando a sitio web no confiable
    private final WebClient webClientPkixBadSsl;
    //webclient apuntando a sitio web confiable, pero sin inyectar certificado cliente
    private final WebClient webClientBadSslWithOutCertificate;
    //webclient apuntando a sitio web confiable, y pasando certificado para verificar nuestra identidad y ejecutar el handshake.
    private final WebClient webClientBadSsl;

    public Mono<String> callBadSslAndReturnPkixError() {
        //vamos a comprobar que si no se reconoce el certificado del sitio web NO CONFIABLE, devolverá una excepción mos un error PKIX
        return webClientPkixBadSsl.get()
                .retrieve()
                //si hay un error, generamos excepción propia
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new ErrorWebClientException(
                                        response.statusCode().value(),
                                        body
                                ))))
                //retornamos la respuesta como un string
                .bodyToMono(String.class);
    }
    public Mono<String> callBadSslWithoutCertificateReturnError() {
        //vamos a comprobar que si no se reconoce el certificado del sitio web, que en ese caso, no existe
        return webClientBadSslWithOutCertificate.get()
                .retrieve()
                //si hay un error, generamos excepción propia
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new ErrorWebClientException(
                                        response.statusCode().value(),
                                        body
                                ))))
                //retornamos la respuesta como un string
                .bodyToMono(String.class);
    }
    public Mono<String> callBadSsl() {
        //vamos a comprobar que si no se reconoce el certificado del sitio web
        return webClientBadSsl.get()
                .retrieve()
                //si hay un error, generamos excepción propia
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new ErrorWebClientException(
                                        response.statusCode().value(),
                                        body
                                ))))
                //retornamos la respuesta como un string
                .bodyToMono(String.class);
    }
}
