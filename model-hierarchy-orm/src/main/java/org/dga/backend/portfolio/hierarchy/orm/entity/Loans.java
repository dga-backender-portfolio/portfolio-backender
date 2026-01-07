package org.dga.backend.portfolio.hierarchy.orm.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Contracts;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("2")
@Data
@ToString(callSuper = true)
public class Loans extends Contracts {

    private LocalDate paymentDueDate;
    private String paymentMethod;
    private String paymentModality;
    private Double percentageTin;
}
