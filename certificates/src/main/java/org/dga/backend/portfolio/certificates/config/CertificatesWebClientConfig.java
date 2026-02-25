package org.dga.backend.portfolio.certificates.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientSsl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
@Log4j2
public class CertificatesWebClientConfig {

    @Bean
    public WebClient webClientPkixBadSsl(WebClient.Builder builder) throws Exception {
        //un cliente "límpio", sin configuración alguna.
        //invocamos a un sitio web en el que nuestra TrustStore, no confía.
        return builder
                .baseUrl("https://untrusted-root.badssl.com")
                // Añadimos un filtro de LOGGING para tu reporting
                .filter((request, next) -> {
                    loggerRequest(request);
                    return next.exchange(request)
                            .doOnNext(this::loggerResponse);
                })
                .build();
    }

    @Bean
    public WebClient webClientBadSslWithOutCertificate(WebClient.Builder builder) throws Exception {
        //un cliente "límpio", sin configuración alguna
        //aquí si reconocemos el sitio web, pero no informamos el certificado
        return builder
                .baseUrl("https://client.badssl.com")
                // Añadimos un filtro de LOGGING para tu reporting
                .filter((request, next) -> {
                    loggerRequest(request);
                    return next.exchange(request)
                            .doOnNext(this::loggerResponse);
                })
                .build();
    }

    @Bean
    public WebClient webClientBadSsl(WebClient.Builder builder, WebClientSsl ssl) throws Exception {
        //un cliente "límpio", sin configuración alguna
        //aquí si reconocemos el sitio web, e indicamos la configuración a través de la propiedades del application.yml
        return builder
                .baseUrl("https://client.badssl.com")
                .apply(ssl.fromBundle("client-badssl"))
                // Añadimos un filtro de LOGGING para tu reporting
                .filter((request, next) -> {
                    loggerRequest(request);
                    return next.exchange(request)
                            .doOnNext(this::loggerResponse);
                })
                .build();
    }
    private void loggerRequest(ClientRequest request){
        log.info("--- Iniciando petición mTLS a {} ---", request.url());
    }
    private void loggerResponse(ClientResponse response){
        log.info("--- Respuesta recibida con estado: {} ---", response.statusCode());
    }
}
