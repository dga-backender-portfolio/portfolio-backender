package org.dga.backend.portfolio.exceptions.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class OutputDto implements Serializable {
    private List<String> output1;
    private String message;
    private String messageError;
}
