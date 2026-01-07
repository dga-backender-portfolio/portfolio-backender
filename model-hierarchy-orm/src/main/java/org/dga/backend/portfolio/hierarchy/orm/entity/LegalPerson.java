package org.dga.backend.portfolio.hierarchy.orm.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Person;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "LEGAL_PERSON")
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LegalPerson extends Person implements Serializable {
    private String socialName;
    private LocalDate constitutionDate;
    private String cnae;
}
