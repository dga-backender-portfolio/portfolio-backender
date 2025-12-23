package org.dga.backend.portfolio.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ExceptionsApp {
    public static void main(String[] args) {
        SpringApplication.run(ExceptionsApp.class, args);
    }
}