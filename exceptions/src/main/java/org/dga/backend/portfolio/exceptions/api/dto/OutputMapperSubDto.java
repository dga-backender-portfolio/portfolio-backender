package org.dga.backend.portfolio.exceptions.api.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OutputMapperSubDto {
    private String param1;
    private String param2;
    private LocalDate param3;
    private Double param4;
}
