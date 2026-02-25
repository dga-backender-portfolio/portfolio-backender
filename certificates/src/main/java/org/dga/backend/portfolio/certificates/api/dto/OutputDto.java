package org.dga.backend.portfolio.certificates.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class OutputDto implements Serializable {
    private String statusResponse;
    private String resultResponse;
}
