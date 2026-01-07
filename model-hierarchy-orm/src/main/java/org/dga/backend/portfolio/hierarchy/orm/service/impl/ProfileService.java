package org.dga.backend.portfolio.hierarchy.orm.service.impl;

import lombok.AllArgsConstructor;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Profile;
import org.dga.backend.portfolio.hierarchy.orm.mapper.MapperProfile;
import org.dga.backend.portfolio.hierarchy.orm.repository.ProfileTptRepository;
import org.dga.backend.portfolio.hierarchy.orm.service.IProfileService;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelProfileGet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileService implements IProfileService {

    private ProfileTptRepository repoProfile;

    public List<ModelProfileGet> getProfiles(){
        /**
         * Si revisamos la query utilizada, vemos que automáticamente, hibernate ¡
         * nos genera un UNION al tener definida la entidad Profile como TABLE_PER_CLASS
         *
         Hibernate:
         select
         p1_0.id,
         p1_0.clazz_,
         p1_0.number_person,
         p1_0.is_client,
         p1_0.load_date,
         p1_0.segmentation,
         p1_0.last_reevaluated_date,
         p1_0.last_scoring,
         p1_0.maximum_outstanding_amount,
         p1_0.maximum_outstanding_number
         from
         (select
         id,
         number_person,
         is_client,
         load_date,
         segmentation,
         null as last_reevaluated_date,
         null as last_scoring,
         null as maximum_outstanding_amount,
         null as maximum_outstanding_number,
         1 as clazz_
         from
         financiability
         union
         all select
         id,
         number_person,
         null as is_client,
         null as load_date,
         null as segmentation,
         last_reevaluated_date,
         last_scoring,
         maximum_outstanding_amount,
         maximum_outstanding_number,
         2 as clazz_
         from
         risks
         ) p1_0
         */
        List<Profile> profiles = repoProfile.findAll();

        return MapperProfile.INSTANCE.map(profiles);
    }

}
