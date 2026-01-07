package org.dga.backend.portfolio.hierarchy.orm.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ModelPersonGet {
    //only a series of fields from each entity
    //person
    private Long numberPerson;
    private String name;
    //physical
    private String surname;
    private LocalDate birthDate;
    //Legal
    private LocalDate constitutionDate;
    private String socialName;

}
