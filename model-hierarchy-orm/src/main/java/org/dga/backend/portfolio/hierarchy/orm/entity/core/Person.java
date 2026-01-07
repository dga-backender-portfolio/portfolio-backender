package org.dga.backend.portfolio.hierarchy.orm.entity.core;


import jakarta.persistence.*;
import lombok.Data;
import org.dga.backend.portfolio.hierarchy.orm.entity.PhysicPerson;

import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person  {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long numberPerson;
    private String name;
    private LocalDate creationDate;


}
