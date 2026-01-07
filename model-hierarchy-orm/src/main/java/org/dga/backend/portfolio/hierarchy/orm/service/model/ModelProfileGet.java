package org.dga.backend.portfolio.hierarchy.orm.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ModelProfileGet {
    //only a series of fields from each entity
    //profile
    private Long numberPerson;
    //financiability
    private LocalDate loadDate;
    private String segmentation;
    private Boolean isClient;
    //risks
    private Integer lastScoring;
    private LocalDate lastReevaluatedDate;
    private Double maximumOutstandingAmount;
    private Integer maximumOutstandingNumber;

}
