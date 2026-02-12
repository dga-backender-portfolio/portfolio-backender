package org.dga.backend.portfolio.circuit_breaker.service.impl;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dga.backend.portfolio.circuit_breaker.exception.internal.ErrorGetInformationException;
import org.dga.backend.portfolio.circuit_breaker.exception.internal.ErrorOpenCircuitBreakerException;
import org.dga.backend.portfolio.circuit_breaker.model.InformationModel;
import org.dga.backend.portfolio.circuit_breaker.service.IExampleService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExampleService implements IExampleService {

    private final WebClient webClient;
    private final CircuitBreakerRegistry registry; // Para consultar el estado real

    // El nombre "requestExample" debe existir en tu application.yml
    @CircuitBreaker(name = "requestExample", fallbackMethod = "fallbackExampleWireMock")
    public Mono<InformationModel> getInformation(Integer id){
        return webClient.get()
                // Esta ruta debe coincidir con el stubFor(urlMatching(...)) de tu WireMock
                .uri("/api/request/id/{id}", id)
                .retrieve()
                // --- ESTA ES LA PIEZA CLAVE ---
                .onStatus(HttpStatusCode::isError, response ->
                        // Lanzamos una excepción explícita
                        response.bodyToMono(String.class).flatMap(error ->
                                Mono.error(new ErrorGetInformationException(error))
                        )
                )
                // ------------------------------
                .bodyToMono(InformationModel.class);
    }
    // Método de rescate (Fallback)
    public Mono<InformationModel> fallbackExampleWireMock(Integer id, Throwable t) {
        io.github.resilience4j.circuitbreaker.CircuitBreaker circuitBreaker = registry.circuitBreaker("requestExample");
        //evaluamos qué tipo de excepción, ya que si no es la que nosotros tratamos en el webClient, presuponemos que es
        //el circuit breaker
        return switch(t){
            case ErrorGetInformationException e -> Mono.error(e);
            default -> Mono.error(new ErrorOpenCircuitBreakerException(
                        201, t.getMessage(), circuitBreaker.getState().toString(), circuitBreaker.getMetrics().getFailureRate() + "%"
                ));
        };

    }
}
