package org.dga.backend.portfolio.exceptions.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class OutputMapperDto {
    private String param1;
    private String param2;
    private Integer param3;
    private List<OutputMapperSubDto> param4;
}
