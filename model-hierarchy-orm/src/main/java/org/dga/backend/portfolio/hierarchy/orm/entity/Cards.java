package org.dga.backend.portfolio.hierarchy.orm.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Contracts;

@Entity
@DiscriminatorValue("1")
@Data
@ToString(callSuper = true)
@NoArgsConstructor
public class Cards extends Contracts {

    private Double maximumComissionAmount;
    private Double maximumRevolvingTae;
    private Double maximumRevolvingTin;
}
