package org.dga.backend.portfolio.hierarchy.orm.entity.core;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONTRACT_TYPE")
@Data
public abstract class Contracts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String contract;
    //Para que la clase discriminatoria pueda ser accesible, la indicamos como read-only
    @Column(name="CONTRACT_TYPE", insertable = false, updatable = false)
    protected String contractType;
    protected String currency;
    protected Long iban;
    protected Double riskAmount;
    protected Double limitAmount;
    protected Double pendingAmount;
    protected Double failedAmount;
    protected Boolean isActive;
    protected Boolean isDeliquency;

}
