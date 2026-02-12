package org.dga.backend.portfolio.circuit_breaker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CircuitBreakerApp {
    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApp.class, args);
    }
}