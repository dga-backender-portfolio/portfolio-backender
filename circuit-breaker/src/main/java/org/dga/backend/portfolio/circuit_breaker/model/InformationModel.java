package org.dga.backend.portfolio.circuit_breaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InformationModel {
    private String name;
    private String document;
    private String category;
}
