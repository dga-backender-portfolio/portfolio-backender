package org.dga.backend.portfolio.circuit_breaker.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.dga.backend.portfolio.circuit_breaker.model.InformationModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import wiremock.com.fasterxml.jackson.databind.JsonNode;
import wiremock.com.jayway.jsonpath.internal.filter.ValueNodes;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Configuration
public class CircuitBreakerConfig {
    @Value("${wiremock.server.port:9090}")
    private int wireMockPort;
    //creamos el wiremock
    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        //creamos la instancia del siremock
        //incluimos el globalTemplating para poder incluir la validación de patrones
        WireMockServer server = new WireMockServer(WireMockConfiguration.wireMockConfig()
                .port(wireMockPort)
                .globalTemplating(true)
        );

        // Configuramos los "Stubs" (comportamientos) por defecto
        // --- CASO OK: id par ---
        server.stubFor(get(urlMatching("/api/request/id/.*[02468]"))
                .atPriority(1)
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"name\": \"Paco Sanz\",\"document\": \"33857392C\",\"category\": \"3\"}")));
        // --- CATCH-ALL (FALLO): Si no entró en la anterior, que será los id's impares ---
        server.stubFor(get(urlMatching("/api/request/id/.*"))
                .atPriority(10) // Prioridad baja, se ejecuta si nada coincide
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"error\": \"ERROR-1338\",\"detail\": \"Error conecting with system data.\"}")));

        return server;
    }

    //Creamos el bean del WebClient apuntando al wiremock
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                // Apuntamos directamente al WireMock local
                .baseUrl("http://localhost:" + wireMockPort)
                .build();
    }
}
