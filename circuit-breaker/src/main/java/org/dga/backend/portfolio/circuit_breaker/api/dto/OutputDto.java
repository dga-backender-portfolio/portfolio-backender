package org.dga.backend.portfolio.circuit_breaker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class OutputDto implements Serializable {
    private String name;
    private String document;
    private String category;
}
