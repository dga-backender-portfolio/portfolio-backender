package org.dga.backend.portfolio.hierarchy.orm.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Profile;

import java.io.Serializable;
import java.time.LocalDate;

@Entity(name = "FINANCIABILITY")
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProfileFinanciability extends Profile  implements Serializable {
    private LocalDate loadDate;
    private String segmentation;
    private Boolean isClient;
}
