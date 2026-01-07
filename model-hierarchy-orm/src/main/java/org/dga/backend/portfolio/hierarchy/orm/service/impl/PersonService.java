package org.dga.backend.portfolio.hierarchy.orm.service.impl;

import lombok.AllArgsConstructor;
import org.aspectj.weaver.patterns.IfPointcut;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Person;
import org.dga.backend.portfolio.hierarchy.orm.entity.core.Profile;
import org.dga.backend.portfolio.hierarchy.orm.mapper.MapperPerson;
import org.dga.backend.portfolio.hierarchy.orm.mapper.MapperProfile;
import org.dga.backend.portfolio.hierarchy.orm.repository.PersonTptRepository;
import org.dga.backend.portfolio.hierarchy.orm.repository.ProfileTptRepository;
import org.dga.backend.portfolio.hierarchy.orm.service.IPersonService;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelPersonGet;
import org.dga.backend.portfolio.hierarchy.orm.service.model.ModelProfileGet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService implements IPersonService {

    private PersonTptRepository repoPerson;

    public List<ModelPersonGet> getPersons(){
        /**
         * Si revisamos la query utilizada, vemos que automáticamente, hibernate ¡
         * nos genera LEFT JOINs al tener definida la entidad Person como JOINED
         *
         * Hibernate:
         *     select
         *         p1_0.id,
         *         case
         *             when p1_1.id is not null
         *                 then 1
         *             when p1_2.id is not null
         *                 then 2
         *         end,
         *         p1_0.creation_date,
         *         p1_0.name,
         *         p1_0.number_person,
         *         p1_1.cnae,
         *         p1_1.constitution_date,
         *         p1_1.social_name,
         *         p1_2.birth_date,
         *         p1_2.city,
         *         p1_2.surname
         *     from
         *         person p1_0
         *     left join
         *         legal_person p1_1
         *             on p1_0.id=p1_1.id
         *     left join
         *         physic_person p1_2
         *             on p1_0.id=p1_2.id
         */
        List<Person> persons = repoPerson.findAll();

        return MapperPerson.INSTANCE.map(persons);
    }

}
