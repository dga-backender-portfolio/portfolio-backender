package org.dga.backend.portfolio.hierarchy.orm.entity.core;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long numberPerson;


}
