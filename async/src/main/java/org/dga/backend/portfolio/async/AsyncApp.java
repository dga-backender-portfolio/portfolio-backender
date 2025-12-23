package org.dga.backend.portfolio.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AsyncApp {
    public static void main(String[] args) {
        SpringApplication.run(AsyncApp.class, args);
    }
}